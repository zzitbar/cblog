package cn.coderme.cblog.controller;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.base.ResultJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;
import sun.security.rsa.RSASignature;

/**
 * Created By Administrator
 * Date:2018/5/9
 * Time:13:32
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson login(String username, String password) {
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
            AuthenticationToken token = new UsernamePasswordToken(username, new String(DigestUtils.md5DigestAsHex(password.getBytes())));
            currentUser.login(token);
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

    public static void main(String[] args) {
        System.out.println(new String(DigestUtils.md5DigestAsHex("123456".getBytes())));
    }
}