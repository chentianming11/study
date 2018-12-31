package com.chen.study.design.pattern.observer;


/**
 * @author 陈添明
 * @date 2018/12/31
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
