package com.chen.study.design.pattern.factory.simple;

import com.chen.study.design.pattern.factory.Pizza;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public class PizzaStore {

    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    public Pizza orderPizza(String type){
        Pizza pizza = simplePizzaFactory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
