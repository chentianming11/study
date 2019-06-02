package com.chen.study.guava.eventbus.event;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class Fruit {

    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
