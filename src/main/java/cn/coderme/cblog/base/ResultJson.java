package cn.coderme.cblog.base;

import java.io.Serializable;

/**
 * 页面Ajax请求后返回结果(JSON)
 */
public class ResultJson<T> implements Serializable {

    private static final long serialVersionUID = -1281040549177326394L;

    public static final String SUCCESS = "success";

    public static final String FAILED = "failed";

    private String status = SUCCESS;

    private String errorMsg;
    private T data; // 返回对象

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

}
