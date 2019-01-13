package com.chen.study.design.pattern.adapter;

/**
 * 火鸡适配器
 * 将火鸡适配成鸭子
 * @author 陈添明
 * @date 2019/1/13
 */
public class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
