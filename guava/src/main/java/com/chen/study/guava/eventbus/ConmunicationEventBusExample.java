package com.chen.study.guava.eventbus;

import com.chen.study.guava.eventbus.service.QueryService;
import com.chen.study.guava.eventbus.service.RequestQueryHandler;
import com.google.common.eventbus.EventBus;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class ConmunicationEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        RequestQueryHandler requestQueryHandler = new RequestQueryHandler(eventBus);
        queryService.query("2312532453453");
        queryService.query("111");
        queryService.query("222");
        queryService.query("333");
        queryService.query("444");

    }
}
