package com.chen.study.concurrent.concurrent3.juc.util.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 显式锁
 * @author 陈添明
 * @date 2018/11/10
 */
public class ReentrantLockExample {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                doSomething();
//            }).start();
//        }


        Thread t1 = new Thread(() -> {
            testUninterrupt();
        });
        t1.start();
        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            testUninterrupt();
        });
        t2.start();
        Thread.sleep(100);

        // 获取处于等锁线程大概数数
        System.out.println(lock.getQueueLength());
        // 是否有处于等锁状态的线程
        System.out.println(lock.hasQueuedThreads());
        // 获取当前线程获取锁的数量
        System.out.println(Thread.currentThread().getName() + lock.getHoldCount());
        // 判断等待线程队列中是否有指定线程
        System.out.println(lock.hasQueuedThread(t1));
        System.out.println(lock.hasQueuedThread(t2));
        // 判断锁是否被线程持有
        System.out.println(lock.isLocked());


    }

    public static void testUninterrupt() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + lock.getHoldCount());
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            while (true) {

            }
        } finally {
            lock.unlock();
        }
    }

    public static void testInterrupt() {
        try {
            // 可被打断的加锁
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void doSomething() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " 执行完成");
    }

    public synchronized static void doSomethingSync() {
        try {
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 执行完成");
    }
}
