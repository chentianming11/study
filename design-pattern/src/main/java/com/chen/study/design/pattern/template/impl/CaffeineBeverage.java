package com.chen.study.design.pattern.template.impl;

/**
 * 咖啡因饮料
 * @author 陈添明
 * @date 2019/1/15
 */
public abstract class CaffeineBeverage {

    /**
     * 冲泡方法
     * 模板算法，定义为final，不可被覆盖
     */
   public final  void prepareRecipe(){
        // ﻿把水煮沸
        boilWater();
        // ﻿用沸水冲泡
        brew();
        // ﻿倒进杯子
        pourInCup();
        // ﻿加调料
       if (customerWantsCondiments()){
            addCondiments();
       }
    }

    private void boilWater() {
        System.out.println("把水煮沸");
    }
    private void pourInCup() {
        System.out.println("把咖啡倒进杯子");
    }

    boolean customerWantsCondiments(){
       return true;
    }

    /**
     * 用沸水冲泡
     */
    protected abstract void brew();
    /**
     * 加入调料
     */
    protected abstract void addCondiments();
}
