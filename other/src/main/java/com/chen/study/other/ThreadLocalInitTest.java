package com.chen.study.other;

/**
 * @author 陈添明
 * @date 2019/9/6
 */
public class ThreadLocalInitTest {

    private static final ThreadLocal<String> stl = ThreadLocal.withInitial(() -> "aaa");

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            String s = stl.get();
            System.out.println(s);
        }).start();

        new Thread(() -> {
            String s = stl.get();
            System.out.println(s);
        }).start();

        new Thread(() -> {
            String s = stl.get();
            System.out.println(s);
        }).start();


        Thread.sleep(3_000);
    }
}
