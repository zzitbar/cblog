package cn.coderme.cblog.controller;

import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:10:43
 */
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
}