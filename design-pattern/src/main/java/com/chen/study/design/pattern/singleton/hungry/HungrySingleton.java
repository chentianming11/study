package com.chen.study.design.pattern.singleton.hungry;

import java.io.Serializable;

/**
 * ﻿饿汉式单例是在类加载的时候就立即初始化，并且创建单例对象。绝对线程安全，
 * 在线程还没出现以前就是实例化了，不可能存在访问安全问题。
 * ﻿优点：没有加任何的锁、执行效率比较高，在用户体验上来说，比懒汉式更好。
 * ﻿缺点：类加载的时候就初始化，不管用与不用都占着空间，可能会浪费内存。
 * 饿汉式 -- 单例模式
 *  不能懒加载
 * @author 陈添明
 * @date 2018/9/9
 */
public class HungrySingleton implements Serializable {

    /**
     * 静态字段初始化实例
     */
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    /**
     * 私有化构造器
     */
    private HungrySingleton(){}

    /**
     * 提供静态方法获取实例
     * @return
     */
    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
