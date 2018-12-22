package com.chen.study.concurrent.concurrent3.juc.util.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author 陈添明
 * @date 2018/11/10
 */
public class SemaphoreExample3 {


    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获取到许可证1");

                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        });

        t1.start();

        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            try {
                // 获取许可证被阻塞时，不可中断
                semaphore.acquireUninterruptibly();
                System.out.println(Thread.currentThread().getName() + "获取到许可证2");
            }  finally {
                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        });

        t2.start();

        Thread.sleep(100);
        t2.interrupt();

    }
}
