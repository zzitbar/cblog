package cn.coderme.cblog.service;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.dao.UserDAO;
import cn.coderme.cblog.entity.User;
import cn.coderme.cblog.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public User findByAppSecret(String appSecret){
        return userDAO.findByAppSecret(appSecret);
    }

    /**
     * 登录校验
     * @param username
     * @param password
     */
    public User loginCheck(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusException("用户名或密码不能为空");
        }
        User user = this.findByUsername(username);
        if (null == user || !Md5Utils.getMD5ofStr(password).equals(user.getPassword())) {
            throw new BusException("用户名或密码错误");
        }
        return user;
    }
}