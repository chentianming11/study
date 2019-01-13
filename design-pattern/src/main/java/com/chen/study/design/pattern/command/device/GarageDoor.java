package com.chen.study.design.pattern.command.device;

/**
 * 车库门
 * @author 陈添明
 * @date 2019/1/7
 */
public class GarageDoor {

    public void up(){
        System.out.println("车库门打开");
    }

    public void down(){
        System.out.println("车库关闭");
    }

    public void stop(){
        System.out.println("车库停止");
    }

    public void lightOn(){
        System.out.println("车库灯打开");
    }

    public void lightOff(){
        System.out.println("车库灯关闭");
    }


}
