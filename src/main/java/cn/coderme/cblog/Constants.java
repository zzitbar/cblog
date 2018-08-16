package cn.coderme.cblog;

/**
 * Created By zhangtengfei
 * Date:2018/4/28
 * Time:11:42
 */
public class Constants {

    public static final String KEY_RATELIMITENABLED = "rateLimitEnabled";
    public static final String KEY_MINUTELIMIT = "minuteLimit";
    public static final String KEY_DAYLIMIT = "dayLimit";
    public static final String KEY_DAYLIMIT_USED = "dayLimitUsed";

    /**
     * bing 国家
     * au：澳大利亚
     * br：巴西
     * ca：加拿大
     * cn：中国
     * de：德国
     * fr：法国
     * gb：英国
     * jp：日本
     * nz：新西兰
     * uk：英国
     * us：美国
     */
    public enum IMAGE_ZONE {
        AU("au"), BR("br"), CA("ca"), CN("cn"), DE("de"), FR("fr"), GB("gb"), JP("jp"), NZ("nz"), UK("uk"), US("us");
        private String value;

        IMAGE_ZONE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}