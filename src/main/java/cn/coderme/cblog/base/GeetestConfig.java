package cn.coderme.cblog.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * geetest.com 配置
 */
@Component
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
    @Value("${geetest.id}")
	private String geetestId;
    @Value("${geetest.key}")
	private String geetestKey;
	private boolean newfailback = true;

    public String getGeetestId() {
        return geetestId;
    }

    public void setGeetestId(String geetestId) {
        this.geetestId = geetestId;
    }

    public String getGeetestKey() {
        return geetestKey;
    }

    public void setGeetestKey(String geetestKey) {
        this.geetestKey = geetestKey;
    }

    public boolean isnewfailback() {
		return newfailback;
	}

}