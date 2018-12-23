package com.chen.study.design.pattern.strategy.behavior;

/**
 * @author 陈添明
 * @date 2018/12/23
 */
public class MuteQuack implements QuackBehavior {
    /**
     * 叫
     */
    @Override
    public void quack() {
        System.out.println("<<沉默>>");
    }
}
