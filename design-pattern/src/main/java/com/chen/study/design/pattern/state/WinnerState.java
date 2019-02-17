package com.chen.study.design.pattern.state;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public class WinnerState implements State {

    private GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    /**
     * 投入硬币
     */
    @Override
    public void insertQuarter() {
        System.out.println("不需要投入硬币");
    }

    /**
     * 退回硬币
     */
    @Override
    public void ejectQuarter() {
        System.out.println("无法退出硬币");
    }

    /**
     * 转动曲柄
     */
    @Override
    public void turnCrank() {
        System.out.println("无法转动曲柄");
    }

    /**
     * 发放糖果
     */
    @Override
    public void dispense() {
        System.out.println("你是赢家，可以一次性得到2颗糖果");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("糖果已售罄");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
}
