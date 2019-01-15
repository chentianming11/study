package com.chen.study.design.pattern.template;

/**
 * @author 陈添明
 * @date 2019/1/14
 */
public class Coffee {

    void prepareRecipe(){
        // ﻿把水煮沸
        boilWater();
        // ﻿用沸水冲泡咖啡
        brewCoffeeGrinds();
        // ﻿把咖啡倒进杯子
        pourInCup();
        // ﻿加糖加牛奶
        addSugarAndMilk();
    }

    private void addSugarAndMilk() {
        System.out.println("加糖加牛奶");
    }

    private void pourInCup() {
        System.out.println("把咖啡倒进杯子");
    }

    private void brewCoffeeGrinds() {
        System.out.println("用沸水冲泡咖啡");
    }

    private void boilWater() {
        System.out.println("把水煮沸");
    }
}
