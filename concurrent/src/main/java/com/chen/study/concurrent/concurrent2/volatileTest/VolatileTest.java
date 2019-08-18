package com.chen.study.concurrent.concurrent2.volatileTest;

/**
 * @author 陈添明
 * @date 2018/9/9
 */
public class VolatileTest {

    /**
     * 如果不使用volatile，程序不会停止
     */
    public volatile static boolean stop = false;

    public static void main(String[] args) throws
            InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true;
        thread.join();
    }
}
