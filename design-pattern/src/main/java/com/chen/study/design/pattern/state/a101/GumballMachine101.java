package com.chen.study.design.pattern.state.a101;

/**
 * @author 陈添明
 * @date 2019/2/17
 */
public class GumballMachine101 {

    /**
     * 糖果售罄
     */
    final static int SOLD_OUT = 0;
    /**
     * 没有硬币
     */
    final static int NO_QUARTER = 1;
    /**
     * 有硬币
     */
    final static int HAS_QUARTER = 2;
    /**
     * 售出糖果
     */
    final static int SOLD = 3;

    /**
     * 当前状态
     */
    private int state = SOLD_OUT;

    /**
     * 售出糖果数
     */
    private int count = 0;

    public GumballMachine101(int count){
        this.count = count;
        if (count > 0){
            state = NO_QUARTER;
        }
    }

    /**
     * 投入硬币
     */
    public void insertQuarter(){
        if (state == HAS_QUARTER){
            System.out.println("你不能重复投入硬币");
        } else if (state == NO_QUARTER){
            state = HAS_QUARTER;
            System.out.println("投入硬币");
        } else if (state == SOLD_OUT){
            System.out.println("无法投入硬币，糖果已售罄");
        } else if (state == SOLD){
            System.out.println("请等待，正在为您准备糖果");
        }
    }

    /**
     * 退回硬币
     */
    public void ejectQuarter(){
        if (state == HAS_QUARTER){
            System.out.println("硬币已退回");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER){
            System.out.println("无法退回,您还未投入硬币");
        } else if (state == SOLD){
            System.out.println("无法退回，您已经转动了曲柄");
        } else if (state == SOLD_OUT){
            System.out.println("无法退回,您还未投入硬币");
        }
    }

    /**
     * 转动曲柄
     */
    public void trunCrank(){
        if (state == SOLD) {
            System.out.println("不能转动2次曲柄");
        } else if (state == NO_QUARTER){
            System.out.println("还没投入硬币");
        } else if (state == SOLD_OUT) {
            System.out.println("糖果已售罄");
        } else if (state == HAS_QUARTER) {
            System.out.println("转动曲柄。。。");
            state = SOLD;
            dispense();
        }
    }

    /**
     * 发放糖果
     */
    private void dispense() {
        if (state == SOLD) {
            System.out.println("售出糖果");
            count--;
            if (count == 0){
                System.out.println("糖果卖完了");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER) {
            System.out.println("需要先投入硬币");
        } else if (state == SOLD_OUT) {
            System.out.println("没有糖果可发放");
        } else if (state == HAS_QUARTER) {
            System.out.println("没有糖果可发放");
        }
    }
}
