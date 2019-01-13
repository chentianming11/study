package com.chen.study.design.pattern.adapter;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public class DuckTestDerive {

    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        WildTurkey wildTurkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);

        System.out.println("======火鸡=======");
        wildTurkey.gobble();
        wildTurkey.fly();

        System.out.println("======鸭子======");
        mallardDuck.quack();
        mallardDuck.fly();

        System.out.println("======鸭子适配器=====");
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
