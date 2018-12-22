package com.chen.study.concurrent.concurrent3.collection.blocking;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/25
 */
public class SynchronousQueueExample {


    /**
     * 1. 无任何容量,
     * 2. 无法获取其中的元素，只能移除
     */
    private SynchronousQueue queue;

    @Before
    public void setup() {
        queue = new SynchronousQueue();
    }


    /**
     * add():
     * 必须有一个线程正在take()处于阻塞等待状态，add操作才能成功
     * 否则会抛出异常：IllegalStateException: Queue full
     */
    @Test
    public void add() throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Object take = queue.take();
                System.out.println("take: " + take);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread.sleep(10);
        System.out.println(queue.add("hello"));


        future.join();
    }


    /**
     * offer(e,timeout):
     * 提交一个数据，最多阻塞timeout时间，timeout之内应该被take消费掉
     * 否则就失败，返回false
     */
    /**
     * 除了take()可以消费数据，剩下的remove() poll()都不能移除数据
     * 同理：element() peek()方法也都取不到数据
     */
    @Test
    public void offer() throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Object take = queue.take();
                System.out.println("take: " + take);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

//        Thread.sleep(10);
        System.out.println(queue.offer("hello", 1, TimeUnit.SECONDS));

        future.join();
    }


    /**
     * put():
     * 阻塞，直到有另一个线程消费
     */
    @Test
    public void put() throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Object take = queue.take();
                System.out.println("take: " + take);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread.sleep(10);
        queue.put("hello");

        future.join();
    }



}
