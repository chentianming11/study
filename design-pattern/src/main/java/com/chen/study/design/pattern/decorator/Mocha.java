package com.chen.study.design.pattern.decorator;

/**
 * 摩卡调料
 * @author 陈添明
 * @date 2018/12/31
 */
public class Mocha extends CondimentDecorator{

    public Mocha(Beverage beverage) {
        super("Mocha-摩卡调料", beverage);
    }

    /**
     * 计算价格
     * @return
     */
    @Override
    public float cost() {
        return 0.99f + this.beverage.cost();
    }
}
