package com.chen.study.design.pattern.adapter;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }

    @Override
    public void fly() {
        System.out.println("飞起来了");
    }
}
