package com.chen.study.design.pattern.decorator;

/**
 * @author 陈添明
 * @date 2018/12/31
 */
public class DecoratorTest {

    public static void main(String[] args) {

        Beverage beverage;

        // 深培咖啡
        beverage = new DrakRoast();

        // 加摩卡
        beverage = new Mocha(beverage);

        // 加奶泡
        beverage = new Whip(beverage);

        System.out.println("总价格：" + beverage.cost());
    }
}
