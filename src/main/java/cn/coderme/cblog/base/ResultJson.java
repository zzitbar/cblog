package cn.coderme.cblog.base;

import java.io.Serializable;

/**
 * 页面Ajax请求后返回结果(JSON)
 */
public class ResultJson<T> implements Serializable {

    private static final long serialVersionUID = -1281040549177326394L;

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    public static final Integer OK = 20000;
    public static final Integer BAD_TOKEN = 50008;
    public static final Integer DUPLICATE_LOGIN = 50012;
    public static final Integer TOKEN_EXPIRAED= 50014;

    private String status = SUCCESS;

    private String errorMsg;
    private T data; // 返回对象
    private Integer code = OK;

    public ResultJson() {
    }

    public ResultJson(Integer code, String errorMsg) {
        this.errorMsg = errorMsg;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static ResultJson ok() {
        return new ResultJson(OK, "Ok");
    }

    public static ResultJson notFound() {
        return new ResultJson(404, "Not Found");
    }

    public static ResultJson badRequest() {
        return new ResultJson(400, "Bad Request");
    }

    public static ResultJson forbidden() {
        return new ResultJson(BAD_TOKEN, "Forbidden");
    }

    public static ResultJson unauthorized() {
        return new ResultJson(401, "unauthorized");
    }

    public static ResultJson serverInternalError() {
        return new ResultJson(500, "Server Internal Error");
    }

    public static ResultJson customerError() {
        return new ResultJson(1001, "customer Error");
    }
}
