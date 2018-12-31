package com.chen.study.design.pattern.decorator;

/**
 * 奶泡调料
 * @author 陈添明
 * @date 2018/12/31
 */
public class Whip extends CondimentDecorator{

    public Whip(Beverage beverage) {
        super("Whip-奶泡调料", beverage);
    }

    /**
     * 计算价格
     * @return
     */
    @Override
    public float cost() {
        return 0.05f + this.beverage.cost();
    }
}
