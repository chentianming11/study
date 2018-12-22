package com.chen.study.concurrent.concurrent3.juc.util.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class CountDownLatchExample3 {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Thread main = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(10_000);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            main.interrupt();
        }).start();

        latch.await(1, TimeUnit.SECONDS);
        System.out.println("===========");
    }
}
