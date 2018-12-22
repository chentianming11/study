package com.chen.study.concurrent.concurrent3.juc.util.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读读不加锁  其它都加锁
 * @author 陈添明
 * @date 2018/11/10
 */
public class ReadWriteLockExample {

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private final static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

//    private static final ReentrantLock lock = new ReentrantLock(true);

    private static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> write());
        t1.start();
        Thread.sleep(10);
        Thread t2 = new Thread(() -> read());
        t2.start();
    }

    public static void write(){
        try {
            // 写锁
            writeLock.lock();
            data.add(System.currentTimeMillis());
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read(){
        try {
            // 读锁
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "===========");
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + "===========");
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }


}
