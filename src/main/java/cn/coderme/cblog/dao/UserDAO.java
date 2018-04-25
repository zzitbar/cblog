package cn.coderme.cblog.dao;

import cn.coderme.cblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:9:44
 */
public interface UserDAO extends JpaRepository<User, Long> {

}