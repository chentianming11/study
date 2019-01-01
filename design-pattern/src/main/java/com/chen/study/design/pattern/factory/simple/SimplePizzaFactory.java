package com.chen.study.design.pattern.factory.simple;

import com.chen.study.design.pattern.factory.CheesePizza;
import com.chen.study.design.pattern.factory.Pizza;
import com.chen.study.design.pattern.factory.VeggiePizza;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public class SimplePizzaFactory {


    public Pizza createPizza(String type){
        Pizza pizza = null;

        if (type.equals("cheese")){
           pizza = new CheesePizza();

        } else if (type.equals("veggie")){
            pizza = new VeggiePizza();

        }
        return pizza;

    }
}
