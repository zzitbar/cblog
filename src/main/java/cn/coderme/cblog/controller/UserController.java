package cn.coderme.cblog.controller;

import cn.coderme.cblog.base.ResultJson;
import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.service.UserService;
import cn.coderme.cblog.utils.JwtUtils;
import cn.coderme.cblog.utils.Md5Utils;
import cn.coderme.cblog.utils.ValidateCodeUtils;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:10:43
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/user";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson info(HttpServletRequest request) {
        ResultJson result = new ResultJson();
        Map map = new HashMap();

        String token = request.getHeader("X-Token");
        Claims claims = JwtUtils.verifyJavaWebToken(token);
        if (null == claims) {
            // TOKEN校验失败
            result = ResultJson.forbidden();
            result.setErrorMsg("TOKEN校验失败");
        } else {
            String userId = claims.getId();
            User user = userService.getById(Long.valueOf(userId));
            map.put("roles", Lists.newArrayList("admin"));
            Map userInfo = new HashMap();
            userInfo.put("name", user.getName());
            userInfo.put("email", user.getEmail());
            userInfo.put("createTime", user.getCreateTime());
            userInfo.put("appSecret", user.getAppSecret());
            userInfo.put("minuteLimit", user.getMinuteLimit());
            userInfo.put("dayLimit", user.getDayLimit());
            userInfo.put("avatar", "http://wx1.sinaimg.cn/orj360/005wDmd2ly1fbv5ypzqxaj30dw0dwjs8.jpg");
            map.put("userInfo", userInfo);
        }
        result.setData(map);
        return result;
    }
}