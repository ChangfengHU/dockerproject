package com.vyibc.user.response;

import java.io.Serializable;

/**
 * Created by Michael on 2017/10/30.
 */
public class Response<T>  implements Serializable {

    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001", "username or password invalid");
    public static final Response USERNAME_EMPTY = new Response("1006", "userinfo is null");
    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("1002", "mobile or email is required");

    public static final Response SEND_VERIFYCODE_FAILED = new Response("1003", "send verify code failed");

    public static final Response VERIFY_CODE_INVALID = new Response("1004", "verifyCode invalid");

    public static final Response SERVICE_ERROR = new Response("1005", "service error");


    public static final Response SUCCESS = new Response();

    private String code;
    private String message;
    //数据总数
    private T  value;

    public Response() {
        this.code = "0";
        this.message = "success";
    }
    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e) {
        return new Response("9999", e.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
