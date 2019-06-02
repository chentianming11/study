package com.chen.study.guava.eventbus.service;

import com.chen.study.guava.eventbus.event.Request;
import com.chen.study.guava.eventbus.event.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class QueryService {


    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }


    public void query(String orderNo){
        System.out.println("收到了一个订单号，交给另一个模块查询：" + orderNo);
        eventBus.post(new Request(orderNo));
    }


    @Subscribe
    public void handleResponse(Response response){
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收到结果啦。。。");
    }
}
