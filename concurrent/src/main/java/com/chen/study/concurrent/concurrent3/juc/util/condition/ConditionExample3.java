package com.chen.study.concurrent.concurrent3.juc.util.condition;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于condition实现的多生产者多消费者示例
 * @author 陈添明
 * @date 2018/11/11
 */
public class ConditionExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final Lock lock = new ReentrantLock();
    private static final Condition produceCondition = lock.newCondition();
    private static final Condition consumerCondition = lock.newCondition();

    private static final LinkedList<Long> timestampPool = new LinkedList<>();

    private static final int max = 5;

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true){
                    produce();
                }
            }).start();
        }

        for (int i = 0; i <3; i++) {
            new Thread(() -> {
                while (true){
                    consumer();
                }
            }).start();
        }




    }




    public static void produce(){
        try {
            lock.lock();
            while (timestampPool.size() >= max){
                System.out.println("队列满了，生产线程" + Thread.currentThread().getName() + "进入等待。。。");
                produceCondition.await();
            }
            long value = System.currentTimeMillis();
            timestampPool.add(value);
            System.out.println(Thread.currentThread().getName() + "-P->" + value);
            Thread.sleep(random.nextInt(300));
            consumerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void consumer(){
        try {
            lock.lock();
            while (timestampPool.size() <= 0){
                System.out.println("队列空了，消费线程" + Thread.currentThread().getName() + "进入等待。。。");
                consumerCondition.await();
            }
            Long value = timestampPool.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C->" + value);
            Thread.sleep(random.nextInt(300));
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
