package com.chen.study.design.pattern.template.impl;

/**
 * @author 陈添明
 * @date 2019/1/15
 */
public class Coffee extends CaffeineBeverage {
    /**
     * 用沸水冲泡
     */
    @Override
    protected void brew() {
        System.out.println("用沸水冲泡咖啡");
    }

    /**
     * 加入调料
     */
    @Override
    protected void addCondiments() {
        System.out.println("加糖加牛奶");
    }
}
