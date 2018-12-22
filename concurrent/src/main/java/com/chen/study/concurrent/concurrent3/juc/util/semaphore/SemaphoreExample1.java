package com.chen.study.concurrent.concurrent3.juc.util.semaphore;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author 陈添明
 * @date 2018/11/10
 */
public class SemaphoreExample1 {

    @Test
    public void test1() throws InterruptedException {
        final SemaphoreLock lock = new SemaphoreLock();
        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "得到锁 SemaphoreLock");
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放锁 SemaphoreLock");
                }
            }).start();

        }
        Thread.currentThread().join();
    }


    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            // 申请一个许可证
            semaphore.acquire();
        }

        public void unlock(){
            // 释放一个许可证
            semaphore.release();
        }
    }
}
