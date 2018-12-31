package com.chen.study.design.pattern.decorator;

/**
 * 饮料抽象类
 * @author 陈添明
 * @date 2018/12/31
 */
public abstract class Beverage {

    protected String description;

    public Beverage(String description){
        this.description = description;
    }

    String getDescription() {
        return this.description;
    }

    /**
     * 计算价格
     * @return
     */
    public abstract float cost();


}
