package cn.coderme.cblog.controller;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.Constants;
import cn.coderme.cblog.base.OpenApiConfig;
import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.config.MyShiroRealm;
import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.service.UserService;
import cn.coderme.cblog.utils.*;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Administrator
 * Date:2018/5/9
 * Time:13:32
 */
@Controller
@RequestMapping("")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired(required = false)
    private GeetestController geetestController;
    @Autowired
    private AliMailUtils aliMailUtils;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MyShiroRealm myShiroRealm;
    @Autowired
    private OpenApiConfig openApiConfig;

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:/admin/index";
        }
        return "register";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 发送邮件
     * @param request
     * @param email
     * @return
     */
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson sendMail(HttpServletRequest request, String email) {
        ResultJson result = new ResultJson();
        try {
            if (!StringUtils.hasText(email)) {
                throw new BusException("邮箱不能为空");
            }
            if (null != userService.findByUsername(email)) {
                throw new BusException("邮箱已存在");
            }
            Map map = geetestController.verify(request);
            String code = ValidateCodeUtils.generate();
            if (map.get("status").equals("success")) {
                aliMailUtils.sendValidateCode(email, code);
                redisUtil.set(email, code, 60*60);
            } else {
                throw new BusException("验证失败，请重试");
            }
        } catch (Exception e) {
            logger.error("发送邮件出错", e);
            result.setStatus(ResultJson.FAILED);
            if (e instanceof BusException) {
                result.setErrorMsg(e.getMessage());
            } else {
                result.setErrorMsg("发送邮件出错");
            }
        }
        return result;
    }

    /**
     * 注册
     * @param email
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson registerinfo(String email, String password, String code, HttpServletRequest request) {
        ResultJson result = new ResultJson();
        try {
            if (!StringUtils.hasText(email) || !StringUtils.hasText(password) || !StringUtils.hasText(code)) {
                throw new BusException("输入项不能为空");
            }
            String value = (String) redisUtil.get(email);
            // 校验
            Map map = geetestController.verify(request);
            if (map.get("status").equals("success")) {
                if (!StringUtils.hasText(value)) {
                    throw new BusException("验证码已过期");
                }
                if (!code.equals(value)) {
                    throw new BusException("验证码错误");
                }
                redisUtil.del(email);
                User user = userService.findByUsername(email);
                if (null != user) {
                    user = new User();
                    user.setCreateTime(new Date());
                    user.setAppSecret(Md5Utils.getMD5ofStr(email, user.getAppSecret())+ValidateCodeUtils.generate().toLowerCase());
                    user.setMinuteLimit(openApiConfig.getMinuteLimit());
                    user.setDayLimit(openApiConfig.getDayLimit());
                }
                user.setUsername(email);
                user.setPassword(Md5Utils.getMD5ofStr(password));
                user.setEmail(email);
                user.setUpdateTime(new Date());

                userService.save(user);

                Map hmap = new HashMap();
                hmap.put(Constants.KEY_RATELIMITENABLED, openApiConfig.getEnabled());
                hmap.put(Constants.KEY_MINUTELIMIT, openApiConfig.getMinuteLimit());
                hmap.put(Constants.KEY_DAYLIMIT, openApiConfig.getDayLimit());

                redisUtil.hmset(user.getAppSecret(), hmap);
            } else {
                throw new BusException("验证失败，请重试");
            }
        } catch (Exception e) {
            logger.error("注册出错", e);
            result.setStatus(ResultJson.FAILED);
            if (e instanceof BusException) {
                result.setErrorMsg(e.getMessage());
            } else {
                result.setErrorMsg("注册出错");
            }
        }
        return result;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson login(String username, String password, HttpServletRequest request) {
        ResultJson result = new ResultJson();
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return result;
        }
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                throw new BusException("用户名或密码不能为空");
            }
            // 校验
            Map map = geetestController.verify(request);
            if (map.get("status").equals("success")) {
                User user = userService.findByUsername(username);
                AuthenticationToken token = new UsernamePasswordToken(username, new String(DigestUtils.md5DigestAsHex(password.getBytes())));
                currentUser.login(token);
                myShiroRealm.setSession("user", user);
            } else {
                throw new BusException("验证失败，请重试");
            }
        } catch (UnknownAccountException uae) {
            result.setErrorMsg("用户名不存在！");
            result.setStatus(ResultJson.FAILED);
        } catch (IncorrectCredentialsException ice) {
            result.setErrorMsg("密码不正确");
            result.setStatus(ResultJson.FAILED);
        } catch (AuthenticationException ae) {
            result.setErrorMsg("用户名或密码不正确");
            result.setStatus(ResultJson.FAILED);
        } catch (Exception e) {
            logger.error("登录出错", e);
            result.setStatus(ResultJson.FAILED);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultJson login(String username, String password) {
//        ResultJson result = new ResultJson();
//        try {
//            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//                throw new BusException("用户名和密码不能为空");
//            }
//            User user = userService.findByUsername(username);
//            if (null == user || !user.getPassword().equals(new String(DigestUtils.md5DigestAsHex(password.getBytes())))) {
//                throw new BusException("用户名或密码错误");
//            }
//            String jwtToken = JwtHelper.createJWT(user.getUsername(),
//                    user.getId().toString(),
//                    "",
//                    audience.getClientId(),
//                    audience.getName(),
//                    audience.getExpiresSecond()*1000,
//                    audience.getBase64Secret());
//            result.setData(jwtToken);
//        } catch (Exception e) {
//            logger.error("登录出错", e);
//            result.setStatus(ResultJson.FAILED);
//            result.setErrorMsg(e.getMessage());
//        }
//        return result;
//    }

//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultJson logout() {
//        ResultJson result = new ResultJson();
//        Subject currentUser = SecurityUtils.getSubject();
//        if (currentUser.isAuthenticated()) {
//            currentUser.logout();
//        }
//        result.setData("");
//        return result;
//    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson changePwd(String username, String password, String newpassword) {
        ResultJson result = new ResultJson();
        try {
            if (!StringUtils.hasText(username) || !StringUtils.hasText(password) || !StringUtils.hasText(newpassword)) {
                throw new BusException("输入框不能为空");
            }
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                throw new BusException("请登录");
            }
            if (!currentUser.getPrincipal().toString().equals(username)) {
                throw new BusException("用户名错误");
            }
            User user = userService.findByUsername(username);
            if (!Md5Utils.getMD5ofStr(password).equals(user.getPassword())) {
                throw new BusException("原密码错误");
            }
            user.setPassword(Md5Utils.getMD5ofStr(newpassword));
            userService.save(user);
        } catch (Exception e) {
            logger.error("修改密码出错", e);
            result.setStatus(ResultJson.FAILED);
            if (e instanceof BusException) {
                result.setErrorMsg(e.getMessage());
            } else {
                result.setErrorMsg("修改密码出错");
            }
        }
        return result;
    }

    /**
     * JWT 登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/jwt/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson loginJwt(String username, String password, HttpServletRequest request) {
        ResultJson result = new ResultJson();
        try {
            // 校验验证码
//            Map map = geetestController.verify(request);
//            if (map.get("status").equals("success")) {
                User user = userService.loginCheck(username, password);
                String token = JwtUtils.createJavaWebToken(user.getId().toString(), user.getUsername(), new HashMap<>(), -1);
                redisUtil.set(user.getId()+Constants.REDIS_TOKEN, token, Constants.TOKEN_EXPIRA);
                result.setData(token);
//            } else {
//                throw new BusException("验证失败，请重试");
//            }
        } catch (Exception e) {
            logger.error("登录出错", e);
            result.setStatus(ResultJson.FAILED);
            if (e instanceof BusException) {
                result.setErrorMsg(e.getMessage());
            } else {
                result.setErrorMsg("登录出错");
            }
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson logout(HttpServletRequest request) {
        ResultJson responseData = ResultJson.ok();
        String token = request.getHeader("X-Token");
        Claims claims = JwtUtils.verifyJavaWebToken(token);
        if (null == claims) {
            // TOKEN校验失败
            responseData = ResultJson.forbidden();
            responseData.setErrorMsg("TOKEN校验失败");
        } else {
            String userId = claims.getId();
            redisUtil.del(userId+ Constants.REDIS_TOKEN);
        }
        return responseData;
    }

    public static void main(String[] args) {
        System.out.println(new String(DigestUtils.md5DigestAsHex("123456".getBytes())));
    }
}