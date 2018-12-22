package com.chen.study.concurrent.concurrent2.singleton;

/**
 * 推荐使用静态内部类实现单例模式
 * 第一次使用的时候才会进行实例化
 * @author 陈添明
 * @date 2018/9/9
 */
public class SingletonHolder {

    private SingletonHolder(){}


    private static class InstanceHolder {
        private static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static SingletonHolder getInstance(){
        return InstanceHolder.INSTANCE;
    }

}
