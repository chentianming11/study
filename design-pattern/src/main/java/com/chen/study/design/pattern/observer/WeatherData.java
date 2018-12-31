package com.chen.study.design.pattern.observer;

import java.util.ArrayList;

/**
 * @author 陈添明
 * @date 2018/12/31
 */
public class WeatherData implements Subject {

    // 观察者集合
    private ArrayList<Observer> observers = new ArrayList<>();

    // 温度
    private float temperature;

    // 湿度
    private float humidity;

    // 气压
    private float pressure;


    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
