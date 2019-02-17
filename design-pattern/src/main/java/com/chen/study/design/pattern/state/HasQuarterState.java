package com.chen.study.design.pattern.state;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    Random random = new Random(System.currentTimeMillis());

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    /**
     * 投入硬币
     */
    @Override
    public void insertQuarter() {
        System.out.println("无法重复投入硬币");
    }

    /**
     * 退回硬币
     */
    @Override
    public void ejectQuarter() {
        System.out.println("已退回硬币");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    /**
     * 转动曲柄
     */
    @Override
    public void turnCrank() {
        System.out.println("转动曲柄。。。");
        int i = random.nextInt(10);
        if (i == 0 && gumballMachine.getCount() > 0) {
            // 赢家状态
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    /**
     * 发放糖果
     */
    @Override
    public void dispense() {
        System.out.println("没有糖果可发放");
    }
}
