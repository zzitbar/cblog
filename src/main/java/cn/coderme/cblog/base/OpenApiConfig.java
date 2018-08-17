package cn.coderme.cblog.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 开放API配置
 * Created By zzitbar
 * Date:2018/8/15
 * Time:16:32
 */
@Component
public class OpenApiConfig {

    @Value("${openapi.rate.limit.enabled}")
    private boolean enabled = true;
    @Value("${openapi.rate.limit.minute}")
    private Integer minuteLimit;
    @Value("${openapi.rate.limit.day}")
    private Integer dayLimit;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getMinuteLimit() {
        return minuteLimit;
    }

    public void setMinuteLimit(Integer minuteLimit) {
        this.minuteLimit = minuteLimit;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }
}