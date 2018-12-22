package com.chen.study.concurrent.concurrent3.juc.util.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class CountDownLatchExample2 {



    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(1);

        /**
         * 第一个线程先执行一部分逻辑后阻塞，等待另外一个线程放行
         */
        new Thread(() -> {
            System.out.println("do some initial working...");
            try {
                Thread.sleep(1_000);
                latch.await();
                System.out.println("do other working...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        /**
         * 第二个线程执行完成后，countdown之后，让第一个线程放行。
         */
        new Thread(() -> {
            System.out.println("async prepare for some data.");
            try {
                Thread.sleep(2_000);
                System.out.println("data prepare done");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();


        new Thread(() -> {
            try {
                latch.await();
                System.out.println("线程3也停止阻塞。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }
}
