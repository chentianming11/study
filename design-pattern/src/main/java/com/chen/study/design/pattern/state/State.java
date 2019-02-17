package com.chen.study.design.pattern.state;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public interface State {
    /**
     * 投入硬币
     */
     void insertQuarter();

    /**
     * 退回硬币
     */
     void ejectQuarter();

    /**
     * 转动曲柄
     */
     void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
