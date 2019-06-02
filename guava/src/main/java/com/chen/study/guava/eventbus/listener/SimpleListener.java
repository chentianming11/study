package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class SimpleListener {

    /**
     * 1. 根据参数的类型进行事件订阅：
     *     String类型的事件，进入这个方法进行处理
     * 2. 将子类监听器注册到event bus中，父类监听器定义的监听也是有效的
     * 3. 可以监听到event类及其子类发送过来的事件。
     * @param event
     */
    @Subscribe
    @AllowConcurrentEvents
    public void doAction(String event){
        System.out.println("处理器1：" + event);
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "处理其1 完成");

    }


    @Subscribe
    @AllowConcurrentEvents
    public void doAction2(String event){
        System.out.println("处理器2：" + event);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "处理2完成");

    }
}
