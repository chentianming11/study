package com.chen.study.design.pattern.command.device;

/**
 * @author 陈添明
 * @date 2019/1/8
 */
public class Stereo {

    public void on(){
        System.out.println("打开音响");
    }


    public void off(){
        System.out.println("关闭音响");
    }

    public void setCd(){
        System.out.println("cd");
    }


    public void setDvd(){
        System.out.println("dvd");
    }

    public void setRadio(){
        System.out.println("广播");
    }

    public void setVolume(int i){
        System.out.println("设置音量：" + i);
    }
}
