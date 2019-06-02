package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class ExceptionListener {


    @Subscribe
    public void m1(String event){
        System.out.println("==========m1=========: " + event);
    }

    @Subscribe
    public void m2(String event){
        System.out.println("==========m2=========: " + event);
    }

    @Subscribe
    public void m3(String event){
//        System.out.println("==========m3=========: " + event);
        throw new RuntimeException("出事了");
    }
}
