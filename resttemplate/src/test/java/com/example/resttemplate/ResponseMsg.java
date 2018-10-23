package com.example.resttemplate;

import java.io.Serializable;

/**
 * @author: xujunqi
 * @date: 2018/9/20 17:45
 * @description:
 */
public class ResponseMsg<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String retCode;

    private String retMsg;

    private T responseBody;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", responseBody=" + responseBody +
                '}';
    }
}