package cn.coderme.cblog.controller;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.OpenApiConfig;
import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.config.MyShiroRealm;
import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.service.UserService;
import cn.coderme.cblog.utils.Md5Utils;
import cn.coderme.cblog.utils.RedisUtil;
import cn.coderme.cblog.utils.ValidateCodeUtils;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By zzitbar
 * Date:2018/8/15
 * Time:14:21
 */
@Controller
@RequestMapping("/openapi")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private MyShiroRealm myShiroRealm;
    @Autowired
    private OpenApiConfig openApiConfig;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("")
    public String index() {
        return "/openapi/index";
    }

    /**
     * 重置 APPsecret
     * @return
     */
    @RequestMapping(value = "/resetAppSecret", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson resetAppSecret() {
        ResultJson result = new ResultJson();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                throw new BusException("请登录");
            }
            User user = userService.findByUsername(currentUser.getPrincipal().toString());
            user.setAppSecret(Md5Utils.getMD5ofStr(user.getEmail(), user.getAppSecret())+ ValidateCodeUtils.generate().toLowerCase());
            user.setUpdateTime(new Date());
            userService.save(user);
            result.setData(user.getAppSecret());

            // 更新 session
            myShiroRealm.setSession("user", user);

            // 更新 redis
            redisUtil.del(user.getAppSecret());
            Map hmap = new HashMap();
            hmap.put(Constants.KEY_RATELIMITENABLED, openApiConfig.getEnabled());
            hmap.put(Constants.KEY_MINUTELIMIT, openApiConfig.getMinuteLimit());
            hmap.put(Constants.KEY_DAYLIMIT, openApiConfig.getDayLimit());

            redisUtil.hmset(user.getAppSecret(), hmap);

        } catch (Exception e) {
            logger.error("重置APPsecret出错", e);
            result.setStatus(ResultJson.FAILED);
            if (e instanceof BusException) {
                result.setErrorMsg(e.getMessage());
            } else {
                result.setErrorMsg("重置APPsecret出错");
            }
        }
        return result;
    }
}