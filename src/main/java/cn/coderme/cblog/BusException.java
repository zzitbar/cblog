package cn.coderme.cblog;

import java.text.MessageFormat;

/**
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:14:50
 */
public class BusException extends RuntimeException {

    private String code;

    public BusException() {
        super();
    }

    public BusException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusException(Throwable cause) {
        super(cause);
    }

    public BusException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusException(String message, Object ... args) {
        super(MessageFormat.format(message, args));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}