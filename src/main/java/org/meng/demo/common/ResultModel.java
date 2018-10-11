package org.meng.demo.common;


/**
 * Result 返回结果
 * @author
 * @date
 */
public class ResultModel<T> {

    private int code;
    private T data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> ResultModel<T> success(T data) {
        ResultModel<T> res = new ResultModel<T>();
        res.setCode(200);
        res.setData(data);
        res.setMessage("success");
        return res;
    }

    public static <T> ResultModel<T> fail(int code, String msg) {
        ResultModel<T> res = new ResultModel<T>();
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }


}
