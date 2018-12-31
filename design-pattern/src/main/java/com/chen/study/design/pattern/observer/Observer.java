package com.chen.study.design.pattern.observer;

/**
 * @author 陈添明
 * @date 2018/12/31
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
