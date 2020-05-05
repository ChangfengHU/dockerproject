package com.vyibc.user.response;

import groovy.transform.ToString;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */
@Data
@ToString
public class CommonResult<T> implements Serializable {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    //数据总数
    private T  value;
    private String ok;	// 不使用

    public static CommonResult build(Integer status, String msg, Object data) {
        return new CommonResult(status, msg, data);
    }

    public static CommonResult ok(Object data) {
        return new CommonResult(data);
    }

    public static CommonResult ok() {
        return new CommonResult(null);
    }

    public static CommonResult errorMsg(String msg) {
        return new CommonResult(500, msg, null);
    }

    public static CommonResult errorMap(Object data) {
        return new CommonResult(501, "error", data);
    }

    public static CommonResult errorTokenMsg(String msg) {
        return new CommonResult(502, msg, null);
    }

    public static CommonResult errorException(String msg) {
        return new CommonResult(555, msg, null);
    }

    public CommonResult() {

    }

//    public static LeeJSONResult build(Integer status, String msg) {
//        return new LeeJSONResult(status, msg, null);
//    }

    public CommonResult(Integer status, String msg,  T value) {
        this.status = status;
        this.msg = msg;
        this.value = value;
    }

    public CommonResult( T value) {
        this.status = 200;
        this.msg = "OK";
        this.value = value;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getValue() {
        return value;
    }

    public void setValue( T value) {
        this.value = value;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

}
