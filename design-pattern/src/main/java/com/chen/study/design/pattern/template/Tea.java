package com.chen.study.design.pattern.template;

/**
 * @author 陈添明
 * @date 2019/1/15
 */
public class Tea {

    void prepareRecipe(){
        // ﻿把水煮沸
        boilWater();
        // ﻿浸泡茶叶
        stepTeaBag();
        // ﻿把茶倒进杯子
        pourInCup();
        // ﻿加柠檬
        addLemon();
    }

    private void addLemon() {
        System.out.println("加柠檬");
    }

    private void pourInCup() {
        System.out.println("把茶倒进杯子");
    }

    private void stepTeaBag() {
        System.out.println("浸泡茶叶");
    }

    private void boilWater() {
        System.out.println("把水煮沸");
    }
}
