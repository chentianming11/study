package com.chen.study.guava.eventbus.event;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class Request {

    private String orderNo;

    @Override
    public String toString() {
        return "Request{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }

    public Request(String orderNo) {
        this.orderNo = orderNo;
    }
}
