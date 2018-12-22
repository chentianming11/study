package com.chen.study.concurrent.concurrent2.threadlocal;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ThreadLocalSimpleTest {

//    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置初始化值，通过重写initialValue方法
     */
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "luo");


    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("chen");

        Thread.sleep(1000);

        String value = threadLocal.get();
        System.out.println(value);
    }
}
