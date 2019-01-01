package com.chen.study.design.pattern.factory.abs;

import com.chen.study.design.pattern.factory.abs.dough.Dough;
import com.chen.study.design.pattern.factory.abs.sauce.Sauce;

/**
 * 原料工厂接口
 * @author 陈添明
 * @date 2019/1/1
 */
public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

}
