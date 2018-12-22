package com.chen.study.concurrent.concurrent2.singleton;

/**
 * 饿汉式 -- 单例模式
 *  不能懒加载
 * @author 陈添明
 * @date 2018/9/9
 */
public class SingletonHungry {

    private static final SingletonHungry INSTANCE = new SingletonHungry();

    // 私有化构造器
    private SingletonHungry(){}

    // 提供静态方法获取实例
    public static SingletonHungry getInstance(){
        return INSTANCE;
    }
}
