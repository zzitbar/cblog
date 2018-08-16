package cn.coderme.cblog.interceptors;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.OpenApiConfig;
import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.service.UserService;
import cn.coderme.cblog.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 接口限流器
 * Created By Administrator
 * Date:2018/5/11
 * Time:15:16
 */
@Component
public class RateLimitingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitingInterceptor.class);

    @Autowired
    private OpenApiConfig openApiConfig;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    private Map<String, Optional<SimpleRateLimiter>> limiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!openApiConfig.getEnabled()) {
            return true;
        }
        String appSecret = request.getHeader("AppSecret");
        // let non-API requests pass
        if (appSecret == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return false;
        }
        Map rateLimitMap = redisUtil.hmget(appSecret);
        if (null == rateLimitMap || rateLimitMap.size() == 0) {
            User user = userService.findByAppSecret(appSecret);
            if (null == user) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
                return false;
            }
            rateLimitMap = new HashMap();
            rateLimitMap.put(Constants.KEY_RATELIMITENABLED, openApiConfig.getEnabled());
            rateLimitMap.put(Constants.KEY_MINUTELIMIT, user.getMinuteLimit());
            rateLimitMap.put(Constants.KEY_DAYLIMIT, user.getDayLimit());
            rateLimitMap.put(Constants.KEY_DAYLIMIT_USED, 0);

            redisUtil.hmset(user.getAppSecret(), rateLimitMap);
            limiters.remove(appSecret);
        }
        SimpleRateLimiter rateLimiter = getRateLimiter(appSecret, rateLimitMap);
        boolean allowRequest = rateLimiter.tryAcquire();

        if (!allowRequest) {
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
            return false;
        }
        Integer todayUsed = (Integer) redisUtil.hget(appSecret, Constants.KEY_DAYLIMIT_USED);
        if (todayUsed.compareTo((Integer) rateLimitMap.get(Constants.KEY_DAYLIMIT)) >= 0) {
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
            return false;
        } else {
            todayUsed = Double.valueOf(redisUtil.hincr(appSecret, Constants.KEY_DAYLIMIT_USED, 1)).intValue();
        }
        response.addHeader("X-RateLimit-Limit", String.valueOf(rateLimitMap.get(Constants.KEY_MINUTELIMIT))+" per min");
//        response.addHeader("X-RateLimit-Today-Used", String.valueOf(todayUsed));

        return allowRequest;
    }

    private SimpleRateLimiter getRateLimiter(String appSecret, Map rateLimitMap) {
        return limiters.computeIfAbsent(appSecret, a -> {
            return Optional.of(SimpleRateLimiter.create((Integer) rateLimitMap.get(Constants.KEY_MINUTELIMIT), TimeUnit.MINUTES));
        }).orElse(null);
    }

    private SimpleRateLimiter createRateLimiter(String clientId) {
        return SimpleRateLimiter.create(5, TimeUnit.MINUTES);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}