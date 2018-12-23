package com.chen.study.design.pattern.strategy.behavior;

/**
 * @author 陈添明
 * @date 2018/12/23
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying...");
    }
}
