package com.chen.study.design.pattern.factory.abs;


import com.chen.study.design.pattern.factory.abs.pizza.CheesePizza;
import com.chen.study.design.pattern.factory.abs.pizza.Pizza;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {

        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (type.equals("cheese")){
            return new CheesePizza(ingredientFactory);
        } else {
            return null;
        }
    }
}
