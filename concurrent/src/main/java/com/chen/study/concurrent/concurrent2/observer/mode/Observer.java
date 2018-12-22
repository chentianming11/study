package com.chen.study.concurrent.concurrent2.observer.mode;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public abstract class Observer {


    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    protected  abstract void update();
}
