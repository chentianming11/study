package com.chen.study.concurrent.concurrent2.gate;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class User extends Thread{

    private String myName;

    private String myAddress;

    private Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }


    @Override
    public void run() {
        System.out.println(myName + "开始进入");
        while (true){
            this.gate.pass(myName, myAddress);
        }
    }
}
