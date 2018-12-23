package com.chen.study.design.pattern.strategy.duck;

import com.chen.study.design.pattern.strategy.behavior.FlyWithWings;
import com.chen.study.design.pattern.strategy.behavior.Quack;

/**
 * @author 陈添明
 * @date 2018/12/23
 */
public class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("我是一只绿头鸭");
    }

    public MallardDuck(){
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }
}
