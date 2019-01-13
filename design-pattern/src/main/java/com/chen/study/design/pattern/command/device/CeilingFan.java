package com.chen.study.design.pattern.command.device;

/**
 * 吊扇
 * @author 陈添明
 * @date 2019/1/13
 */
public class CeilingFan {

    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    String location;
    int speed;

    public CeilingFan(String location) {
        this.location = location;
    }

    public void high(){
        // 设置高转速
        this.speed = HIGH;
    }

    public void mediun(){
        // 设置中转速
        this.speed = MEDIUM;
    }

    public void low(){
        // 设置低转速
        this.speed = LOW;
    }

    public void off(){
        // 关闭吊扇
        this.speed = OFF;
    }

    public int getSpeed(){
        return speed;
    }
}
