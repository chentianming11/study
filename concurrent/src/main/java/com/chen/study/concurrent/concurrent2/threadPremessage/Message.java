package com.chen.study.concurrent.concurrent2.threadPremessage;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
