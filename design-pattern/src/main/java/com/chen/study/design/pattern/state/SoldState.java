package com.chen.study.design.pattern.state;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public class SoldState implements State {

    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    /**
     * 投入硬币
     */
    @Override
    public void insertQuarter() {
        System.out.println("请等待，正在为您准备糖果");
    }

    /**
     * 退回硬币
     */
    @Override
    public void ejectQuarter() {
        System.out.println("无法退回，您已经转动了曲柄");
    }

    /**
     * 转动曲柄
     */
    @Override
    public void turnCrank() {
        System.out.println("不能转动2次曲柄");
    }

    /**
     * 发放糖果
     */
    @Override
    public void dispense() {
        System.out.println("售出糖果");
        gumballMachine.setCount(gumballMachine.getCount()-1);
        if (gumballMachine.getCount() == 0){
            System.out.println("糖果卖完了");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
    }
}
