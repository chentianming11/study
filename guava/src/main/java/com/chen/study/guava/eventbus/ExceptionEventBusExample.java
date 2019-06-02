package com.chen.study.guava.eventbus;

import com.chen.study.guava.eventbus.listener.ExceptionListener;
import com.google.common.eventbus.EventBus;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class ExceptionEventBusExample {

    public static void main(String[] args) {
        // 创建一个eventBus 并指定异常处理
        EventBus eventBus = new EventBus(((exception, context) -> {
            System.out.println(exception.getMessage());
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        }));
        // 注册一个监听器
        eventBus.register(new ExceptionListener());
        // 发送一个事件
        eventBus.post("exception post");
    }

}
