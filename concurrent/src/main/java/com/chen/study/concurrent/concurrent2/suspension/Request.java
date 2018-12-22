package com.chen.study.concurrent.concurrent2.suspension;

/**
 * @author 陈添明
 * @date 2018/9/22
 */
public class Request {


    private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
