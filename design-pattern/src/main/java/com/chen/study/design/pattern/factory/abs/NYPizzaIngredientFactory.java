package com.chen.study.design.pattern.factory.abs;

import com.chen.study.design.pattern.factory.abs.dough.Dough;
import com.chen.study.design.pattern.factory.abs.dough.ThinCrustDough;
import com.chen.study.design.pattern.factory.abs.sauce.MatrinaraSauce;
import com.chen.study.design.pattern.factory.abs.sauce.Sauce;

/**
 * 纽约披萨原料工厂
 * @author 陈添明
 * @date 2019/1/1
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MatrinaraSauce();
    }
}
