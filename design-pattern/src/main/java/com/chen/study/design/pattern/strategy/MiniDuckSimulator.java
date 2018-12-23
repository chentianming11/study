package com.chen.study.design.pattern.strategy;

import com.chen.study.design.pattern.strategy.duck.Duck;
import com.chen.study.design.pattern.strategy.duck.MallardDuck;

/**
 * @author 陈添明
 * @date 2018/12/23
 */
public class MiniDuckSimulator {


    public static void main(String[] args) {
        Duck duck = new MallardDuck();

        duck.display();
        duck.performFly();
        duck.performQuack();
    }
}
