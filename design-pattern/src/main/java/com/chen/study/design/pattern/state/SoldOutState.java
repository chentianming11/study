package com.chen.study.design.pattern.state;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public class SoldOutState implements State {

    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    /**
     * 投入硬币
     */
    @Override
    public void insertQuarter() {
        System.out.println("无法投入硬币，糖果已售罄");
    }

    /**
     * 退回硬币
     */
    @Override
    public void ejectQuarter() {
        System.out.println("无法退回,您还未投入硬币");
    }

    /**
     * 转动曲柄
     */
    @Override
    public void turnCrank() {
        System.out.println("糖果已售罄");
    }

    /**
     * 发放糖果
     */
    @Override
    public void dispense() {
        System.out.println("没有糖果可发放");
    }
}
