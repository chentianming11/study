package com.chen.study.design.pattern.singleton;

/**
 * 枚举方式 -- 单例模式
 * @author 陈添明
 * @date 2018/9/9
 */
public class SingletonEnum {

    private SingletonEnum(){}

    private enum Singleton {

        INSTANCE;

        private final SingletonEnum instance;

        public SingletonEnum getInstance(){
            return instance;
        }

        Singleton(){
            instance = new SingletonEnum();
        }

    }

    public static SingletonEnum getInstance(){
        return Singleton.INSTANCE.getInstance();
    }





}
