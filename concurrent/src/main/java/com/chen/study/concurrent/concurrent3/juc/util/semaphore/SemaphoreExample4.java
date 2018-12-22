package com.chen.study.concurrent.concurrent3.juc.util.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author 陈添明
 * @date 2018/11/10
 */
public class SemaphoreExample4 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                // 排干所有的许可证，即获取所有的许可证
                semaphore.drainPermits();
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
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获取到许可证2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        });

        t2.start();

        while (true) {
            System.out.println("当前可用的许可证数量：" + semaphore.availablePermits());
            System.out.println("队列长度(评估出在等待的线程数量)" + semaphore.getQueueLength());
            System.out.println("是有有正在等待获取信号量的线程：" + semaphore.hasQueuedThreads());
            System.out.println("======================");
            Thread.sleep(1_000);
        }


    }
}
