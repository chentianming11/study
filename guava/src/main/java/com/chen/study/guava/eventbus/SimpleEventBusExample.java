package com.chen.study.guava.eventbus;

import com.chen.study.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class SimpleEventBusExample {


    @Test
    public void test1() {
        // 创建一个eventBus
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));


        // 注册一个监听器
        eventBus.register(new SimpleListener());


        // 发送一个事件
       System.out.println(Thread.currentThread().getName() + "发送第一个");
       eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第二个");
        eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第三个");
        eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第四个");
        eventBus.post("我来了");


    }


    @Test
    public void test2() {
        // 创建一个eventBus
        EventBus eventBus = new EventBus();

        // 注册一个监听器
        eventBus.register(new SimpleListener());

        // 发送一个事件
        System.out.println(Thread.currentThread().getName() + "发送第一个");
        eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第二个");
        eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第三个");
        eventBus.post("我来了");

        System.out.println(Thread.currentThread().getName() + "发送第四个");
        eventBus.post("我来了");



    }
}
