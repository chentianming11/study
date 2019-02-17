package com.chen.study.design.pattern.state;


import lombok.Data;

@Data
public class GumballMachine {
    /**
     * 糖果售罄
     */
    private State soldOutState;
    /**
     * 没有硬币
     */
    private State noQuarterState;
    /**
     * 有硬币
     */
    private State hasQuarterState;
    /**
     * 售出糖果
     */
    private State soldState;

    private State winnerState;

    /**
     * 当前状态
     */
    private State state = soldOutState;

    /**
     * 售出糖果数
     */
    private int count = 0;

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0){
            state = noQuarterState;
        }
    }

    /**
     * 投入硬币
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * 退回硬币
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * 转动曲柄
     */
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    /**
     * 释放一个糖果
     */
    public void releaseBall(){
        System.out.println("将要放出一个糖果");
        if (count > 0) {
            count--;
        }
    }
}