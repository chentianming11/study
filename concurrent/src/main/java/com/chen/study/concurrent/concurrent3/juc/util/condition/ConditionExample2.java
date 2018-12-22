package com.chen.study.concurrent.concurrent3.juc.util.condition;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class ConditionExample2 {

    /**
     * 1. 只使用公平加锁，不使用 lock and condition，实现生产者单消费者有问题吗？
     *     答：有问题，因为公平加锁并不是绝对的公平
     * 2. condition的await()是否会放弃锁
     *      答：await()会自动放弃锁，进入waitSet中，必须由其它线程调用lock的signal()或者signalAll()方法
     *       才可能重新被唤醒。
     * 3. 能否只是用condition，不使用lock实现。
     *      答：不能，因为condition.await() 和 signal()方法调用的时候必须处于持有锁的状态
     *
     */

    private final static ReentrantLock lock = new ReentrantLock(true);


    private static int data = 0;


    private static void buildData() {
        try {
            lock.lock();  // 等价 synchronized
            data++;
            System.out.println(Thread.currentThread().getName() + ": 生产" + data);
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void useData(){
        try {
            lock.lock();
            // 使用数据
            System.out.println(Thread.currentThread().getName() + "：消费" + data);
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                buildData();
            }
        }).start();

        new Thread(() -> {
            while (true){
                useData();
            }
        }).start();
    }


}
