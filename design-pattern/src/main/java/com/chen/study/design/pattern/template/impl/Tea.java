package com.chen.study.design.pattern.template.impl;

/**
 * @author 陈添明
 * @date 2019/1/15
 */
public class Tea extends CaffeineBeverage {
    /**
     * 用沸水冲泡
     */
    @Override
    protected void brew() {
        System.out.println("浸泡茶叶");
    }

    /**
     * 加入调料
     */
    @Override
    protected void addCondiments() {
        System.out.println("加柠檬");
    }
}
