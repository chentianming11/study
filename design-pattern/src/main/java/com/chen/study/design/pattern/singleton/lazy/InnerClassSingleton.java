package com.chen.study.design.pattern.singleton.lazy;

import java.io.Serializable;

/**
 * 推荐使用静态内部类实现单例模式
 * 第一次使用的时候才会进行实例化
 * @author 陈添明
 * @date 2018/9/9
 */
public class InnerClassSingleton implements Serializable {

    /**
     * 私有化构造器
     */
    private InnerClassSingleton(){}

    public static InnerClassSingleton getInstance(){
        // 在返回结果以前， 一定会先加载内部类
        return InstanceHolder.INSTANCE;
    }

    /**
     * 静态内部类在没有调用的时候，不会加载
     */
    private static class InstanceHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 新增readResolve()方法，防止反序列化破坏单例
     * @return
     */
    private Object readResolve(){
        return getInstance();
    }

}
