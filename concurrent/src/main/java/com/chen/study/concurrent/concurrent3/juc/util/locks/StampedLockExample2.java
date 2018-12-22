package com.chen.study.concurrent.concurrent3.juc.util.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * 带时间戳的锁
 * StampedLock是Java8引入的一种新的所机制,简单的理解,可以认为它是读写锁的一个改进版本,读写锁虽然分离了读和写的功能,使得读与读之间可以完全并发,但是读和写之间依然是冲突的,读锁会完全阻塞写锁,它使用的依然是悲观的锁策略.如果有大量的读线程,他也有可能引起写线程的饥饿
 * 而StampedLock则提供了一种乐观的读策略,这种乐观策略的锁非常类似于无锁的操作,使得乐观锁完全不会阻塞写线程
 *
 * @author 陈添明
 * @date 2018/11/11
 */
public class StampedLockExample2 {


    /**
     * 1. ReentrantLock  vs Synchronized
     * 1.1 ReentrantLock性能稍微更好一些
     * 1.2 ReentrantLock使用起来更加灵活
     * 2. ReentrantReadWriteLock
     * 1.1 读读不加锁  写写，读写，写读加锁
     * 1.2 问题：有99个线程进行读，1个线程进行写，就会导致写很难拿到锁（因为概率太低）
     * 即写饥饿问题。读的效率很高，写的效率很低。
     */

    private final static StampedLock lock = new StampedLock();
    private final static List<Long> data = new ArrayList<>();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            while (true) {
                read();
            }
        };

        Runnable writeTask = () -> {
            while (true) {
                write();
            }
        };

        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(writeTask);

    }

    /**
     * 乐观读
     */
    private static void read() {
        while (true) {
            long stamp = lock.tryOptimisticRead();
            String value = data.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""));
            System.out.println(value);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lock.validate(stamp)){
                return;
            }
            System.out.println("读失败，重读");
        }



    }

    private static void write() {
        long stamped = -1;
        try {
            long value = System.currentTimeMillis();
            stamped = lock.writeLock();
            data.add(value);
            System.out.println("W-" + value);
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }


}
