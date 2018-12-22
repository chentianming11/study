package com.chen.study.concurrent.concurrent2.observer.mode;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题 - 事件源
 * @author 陈添明
 * @date 2018/9/16
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    @Getter
    private int state;

    public void setState(int state){
        if (state == this.state){
            return;
        }

        this.state = state;
        // 通知
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){
        observers.forEach(Observer::update);
    }






}
