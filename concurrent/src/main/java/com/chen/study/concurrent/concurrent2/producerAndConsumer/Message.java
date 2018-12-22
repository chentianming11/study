package com.chen.study.concurrent.concurrent2.producerAndConsumer;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class Message {

    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                '}';
    }
}
