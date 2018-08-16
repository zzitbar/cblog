package cn.coderme.cblog.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云邮件发送服务配置
 * Created By Administrator
 * Date:2018/8/14
 * Time:11:45
 */
@Component
public class MailKey {

    @Value("${aliyun.smtp.username}")
    private String username;
    @Value("${aliyun.smtp.password}")
    private String password;
    @Value("${aliyun.smtp.host}")
    private String host;
    @Value("${aliyun.smtp.port}")
    private String port;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}