package com.chen.study.design.pattern.observer;

/**
 * @author 陈添明
 * @date 2018/12/31
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    // 温度
    private float temperature;

    // 湿度
    private float humidity;

    private Subject subject;

    public CurrentConditionDisplay(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        String format = String.format("当前温度：%s, 湿度：%s", temperature, humidity);
        System.out.println(format);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
