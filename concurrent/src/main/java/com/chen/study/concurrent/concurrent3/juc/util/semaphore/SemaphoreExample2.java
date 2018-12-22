package com.chen.study.concurrent.concurrent3.juc.util.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author 陈添明
 * @date 2018/11/10
 */
public class SemaphoreExample2 {

    /**
     * 应用场景：连接池-允许多个线程到池子里面获取连接
     * 当无法获取到连接时
     * 1. 等待超时抛出异常
     * 2. 阻塞直到获取到连接
     * 3. 放弃
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 2; i++)
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " in. ");

                try {
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + "获取到信号量");
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);
                System.out.println(Thread.currentThread().getName() + " out. ");
            }).start();


        while (true) {
            System.out.println("当前可用的许可证数量：" + semaphore.availablePermits());
            System.out.println("队列长度(评估出在等待的线程数量)" + semaphore.getQueueLength());
            System.out.println("======================");
            Thread.sleep(1_000);
        }
    }
}
