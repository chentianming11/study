package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class MultipleEventListener {


    @Subscribe
    public void task1(String event){
        System.out.println("task1接收到一个String事件：" + event);
    }

    @Subscribe
    public void task2(String event){
        System.out.println("task2接收到一个String事件：" + event);
    }

    @Subscribe
    public void task3(Integer event){
        System.out.println("task3接收到一个Integer事件：" + event);
    }


}
