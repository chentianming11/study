package com.chen.study.concurrent.concurrent2.immutable;

/**
 * 不可变对象模式
 * 1. 不可变对象一定是线程安全的，该对象的任何属性都不可被修改
 * 2. 可变对象不一定是线程不安全的，，StringBuffer是可变对象，但是是线程安全的，因为方法都被sync修饰。
 * @author 陈添明
 * @date 2018/9/19
 */
final public class Person {

    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
