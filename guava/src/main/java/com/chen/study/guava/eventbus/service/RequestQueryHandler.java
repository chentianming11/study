package com.chen.study.guava.eventbus.service;

import com.chen.study.guava.eventbus.event.Request;
import com.chen.study.guava.eventbus.event.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class RequestQueryHandler {

    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }


    @Subscribe
    public void doQuery(Request request){
        System.out.println("查询到了结果，并通过事件扔回去");
        Response response = new Response();
        eventBus.post(response);
    }
}
