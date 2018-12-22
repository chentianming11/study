package com.chen.study.concurrent.concurrent3.juc.util.condition;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class ConditionExample1 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static AtomicBoolean noUse = new AtomicBoolean(true);


    private static void buildData() {
        try {
            lock.lock();  // 等价 synchronized
            while (noUse.get()){
                // 如果数据还没有使用，等待
                condition.await();  // 等价 monitor.wait()
            }
            // 数据被使用了，构建新数据
            data++;
            System.out.println(Thread.currentThread().getName() + ": 生产" + data);
            Thread.sleep(1_000);
            noUse.set(true);
            condition.signal();  // 等价 monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void useData(){
        try {
            lock.lock();
            while (!noUse.get()){
                // 数据已经使用了，等待
                condition.await();
            }
            // 使用数据
            System.out.println(Thread.currentThread().getName() + "：消费" + data);
            Thread.sleep(1_000);
            noUse.set(false);
            condition.signal();
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
