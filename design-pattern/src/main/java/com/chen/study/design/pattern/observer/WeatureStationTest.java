package com.chen.study.design.pattern.observer;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/12/31
 */
public class WeatureStationTest {

    public static void main(String[] args) {

        // 创建天气数据
        WeatherData weatherData = new WeatherData();

        // 创建当前状况气象版
        CurrentConditionDisplay display = new CurrentConditionDisplay(weatherData);

        while (true){
            Random random = new Random(System.currentTimeMillis());
            weatherData.setMeasurements(random.nextFloat(),random.nextFloat(), random.nextFloat());
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
