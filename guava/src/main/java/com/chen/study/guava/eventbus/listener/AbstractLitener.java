package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public abstract class AbstractLitener {


    @Subscribe
    public void commonTask(String event){
        System.out.println("commonTask: 收到一个String事件 -> " + event);
    }
}
