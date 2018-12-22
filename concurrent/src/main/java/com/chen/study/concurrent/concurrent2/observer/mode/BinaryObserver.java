package com.chen.study.concurrent.concurrent2.observer.mode;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class BinaryObserver extends Observer {


    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("Binary String: " + Integer.toBinaryString( subject.getState()));
    }
}
