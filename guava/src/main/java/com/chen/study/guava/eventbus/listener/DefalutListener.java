package com.chen.study.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class DefalutListener extends AbstractLitener {

    @Subscribe
    public void defaultTask(String event){
        System.out.println("defaultTask: 收到一个String事件 -> " + event);
    }

}
