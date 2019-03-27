package cn.coderme.cblog.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created By zzitbar
 * Date:2018/10/30
 * Time:14:18
 */
@Component
public class WxConfig {

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.appsecret}")
    private String appsecret;

    @Value("${weixin.token}")
    private String token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}