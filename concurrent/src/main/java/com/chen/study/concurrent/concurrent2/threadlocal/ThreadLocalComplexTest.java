package com.chen.study.concurrent.concurrent2.threadlocal;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ThreadLocalComplexTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set("thread-t1");
            threadLocal.set("thread-t111111");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            threadLocal.set("thread-t2");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
    }

}
