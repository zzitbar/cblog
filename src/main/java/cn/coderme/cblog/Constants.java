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

    public static final String CATEGORY_LIST_REDIS_KEY = "categoryList";

    public static final String REDIS_TOKEN = "_TOKEN";

    public static final Long TOKEN_EXPIRA = 7200L; // TOKEN 失效时间（秒）

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

    public enum CHART_TYPE {
        LINE("line"), SPLINE("spline"), COLUMN("column"), PIE("pie"), BAR("bar");
        private String value;

        CHART_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * API 统计分类类型
     * 1：今日
     * 2：昨日
     * 3：7天
     * 4：30天
     */
    public enum API_REPORT_DURATION {
        TODAY(1), YESTERDAY(2), SEVEN_DAYS(3), THIRTY_DAYS(4);
        private Integer value;

        API_REPORT_DURATION(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public static String convert(Integer value) {
            if (TODAY.getValue().equals(value)) {
                return "今日";
            } else if (YESTERDAY.getValue().equals(value)) {
                return "昨日";
            } else if (SEVEN_DAYS.getValue().equals(value)) {
                return "7天";
            } else if (THIRTY_DAYS.getValue().equals(value)) {
                return "30天";
            } else {
                return "";
            }
        }
    }

    public enum CATEGORY_ID {
        WEIBO(0), WEIXIN(1), BAIDU(2), ZHIHU(3), SOGOU(4), TOUTIAO(5), DOUBAN(6);
        private Integer value;

        CATEGORY_ID(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}