package com.chen.study.design.pattern.adapter;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("咯咯叫");
    }

    @Override
    public void fly() {
        System.out.println("只能飞一点距离");
    }
}
