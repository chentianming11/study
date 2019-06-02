package com.chen.study.guava.eventbus;

import com.chen.study.guava.eventbus.event.Apple;
import com.chen.study.guava.eventbus.listener.FruitListener;
import com.google.common.eventbus.EventBus;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class FruitEventBusExample {



    public static void main(String[] args) {
        // 创建一个eventBus
        EventBus eventBus = new EventBus();
        // 注册一个监听器
        eventBus.register(new FruitListener());
        // 发送一个事件
       eventBus.post(new Apple("apple"));
    }
}
