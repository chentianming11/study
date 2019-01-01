package com.chen.study.design.pattern.factory.method;

import com.chen.study.design.pattern.factory.Pizza;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")){
            return new NYCheesePizza();
        } else {
            return null;
        }
    }
}
