package cn.coderme.cblog.service;

import cn.coderme.cblog.dao.UserDAO;
import cn.coderme.cblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:10:44
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findByUsername(String username){
        return userDAO.findByUsername(username);
    }
}