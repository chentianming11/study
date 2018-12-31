package com.chen.study.design.pattern.decorator;

/**
 * 抽象调料装饰者
 * 装饰饮料
 * @author 陈添明
 * @date 2018/12/31
 */
public abstract class CondimentDecorator extends Beverage{

    /**
     * 被装饰对象
     */
    protected Beverage beverage;

    public CondimentDecorator(String description, Beverage beverage) {
        super(description);
        this.beverage = beverage;
    }
}
