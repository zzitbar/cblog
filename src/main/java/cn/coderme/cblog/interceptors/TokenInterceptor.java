package cn.coderme.cblog.interceptors;

import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.utils.JwtUtils;
import cn.coderme.cblog.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created By Administrator
 * Date:2018/9/25
 * Time:15:12
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResultJson responseData = ResultJson.ok();
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
//            responseMessage(response, response.getWriter(), responseData);
//            return super.preHandle(request, response, handler);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("X-Token");
        if (!StringUtils.hasText(token)) {
            responseData = ResultJson.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        Claims result = JwtUtils.verifyJavaWebToken(token);
        if (null == result) {
            // TOKEN校验失败
            responseData = ResultJson.forbidden();
            responseData.setErrorMsg("TOKEN校验失败");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        String userId = result.getId();
        String value = (String) redisUtil.get(userId+ Constants.REDIS_TOKEN);
        if (!StringUtils.hasText(value) || !value.equals(token)) {
            responseData = ResultJson.forbidden();
            responseData.setErrorMsg("TOKEN已失效");
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        redisUtil.expire(userId+ Constants.REDIS_TOKEN, Constants.TOKEN_EXPIRA);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultJson responseData) {
        responseData = ResultJson.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
}