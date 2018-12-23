package com.chen.study.design.pattern.strategy.behavior;

/**
 * @author 陈添明
 * @date 2018/12/23
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly!!!");
    }
}
