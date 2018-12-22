package com.chen.study.concurrent.concurrent2.observer.mode;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class OctalObserver extends Observer {


    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("Octal String: " +Integer.toOctalString( subject.getState()));
    }
}
