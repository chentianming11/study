package com.chen.study.guava.eventbus;

import com.chen.study.guava.eventbus.listener.MultipleEventListener;
import com.google.common.eventbus.EventBus;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class MultipleEventBusExample {


    public static void main(String[] args) {
        // 创建一个eventBus
        EventBus eventBus = new EventBus();
        // 注册一个监听器
        eventBus.register(new MultipleEventListener());
        // 发送一个事件
       eventBus.post("发送字符串事件");
       eventBus.post(1);
    }
}
