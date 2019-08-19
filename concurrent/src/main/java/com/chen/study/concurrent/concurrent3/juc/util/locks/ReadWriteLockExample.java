package com.chen.study.concurrent.concurrent3.juc.util.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public static void main(String[] args) {
        /**
         * 显示线程饥饿问题  读很多导致写很难执行
         */
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(ReadWriteLockExample::read);
        executorService.execute(ReadWriteLockExample::write);
        for (int i = 0; i < 100; i++) {
            executorService.execute(ReadWriteLockExample::read);
        }

    }

    public static void write(){
        try {
            // 写锁
            writeLock.lock();
            data.add(System.currentTimeMillis());
            System.out.println("-------- write finish ----------");
            Thread.sleep(2_000);
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
            System.out.println(Thread.currentThread().getName() + "======read start=====");
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + "=======read end====");
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }


}
