package com.chen.study.design.pattern.strategy.pay;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class PayTest {

    public static void main(String[] args) {
        Order order = new Order("1", "201904013343543", 300);
        PayState payState = order.pay();
        System.out.println(payState);
    }
}
