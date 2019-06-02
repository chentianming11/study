package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event){
        System.out.println("---------处理DeadEvent--------");
        System.out.println(event.getSource());
        System.out.println(event.getEvent());

    }
}
