package com.chen.study.design.pattern.singleton.lazy;

/**
 * 懒汉式 - 单例模式
 * 使用double-check解决线程安全问题
 *
 * 可能会引发空指针异常
 * 原因：编译器优化，可能出现 --- 第一个线程实例化SingletonLazy还没有完成
 * 第二个线程直接去使用了instance，这时候，可能会用到instance的中为null的字段
 * 从而导致空指针异常。
 *
 * 解决方案：使用volatile解决可能出现的空指针异常。
 * @author 陈添明
 * @date 2018/9/9
 */
public class LazySingleton {

    /**
     * 使用volatile防止因为指令重排序导致空指针异常。
     */
    private static volatile LazySingleton instance;

    private LazySingleton(){}

    public static LazySingleton getInstance(){

        // double-check加锁
        if (instance == null) {
            synchronized (LazySingleton.class){
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }

        }
        return instance;
    }
}
