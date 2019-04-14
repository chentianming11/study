package com.chen.study.design.pattern.adapter.ac2dc;



public class AC220 implements IAC220 {

    @Override
    public int outputAC220V(){
        int output = 220;
        System.out.println("输出交流电" + output + "V");
        return output;
    }
}