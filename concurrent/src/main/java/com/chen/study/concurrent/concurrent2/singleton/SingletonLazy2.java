package com.chen.study.concurrent.concurrent2.singleton;

/**
 * 懒汉式 - 单例模式
 * 使用double-check解决线程安全问题
 * 使用volatile解决可能出现的空指针异常。
 * @author 陈添明
 * @date 2018/9/9
 */
public class SingletonLazy2 {


    private static volatile SingletonLazy2 instance;

    private SingletonLazy2(){}

    public static SingletonLazy2 getInstance(){

        if (instance == null) {
            synchronized (SingletonLazy2.class){
                if (instance == null) {
                    instance = new SingletonLazy2();
                }
            }

        }
        return instance;
    }


}
