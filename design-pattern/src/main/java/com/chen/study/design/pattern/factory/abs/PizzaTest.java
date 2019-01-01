package com.chen.study.design.pattern.factory.abs;

import com.chen.study.design.pattern.factory.abs.pizza.Pizza;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public class PizzaTest {

    public static void main(String[] args) {

        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");
    }
}
