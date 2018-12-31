package com.chen.study.design.pattern.decorator;

/**
 * 深培咖啡
 * @author 陈添明
 * @date 2018/12/31
 */
public class DrakRoast extends Beverage{

    public DrakRoast() {
        super("DrakRoast-深培咖啡");
    }

    /**
     * 计算价格
     *
     * @return
     */
    @Override
    public float cost() {
        return 1.99f;
    }
}
