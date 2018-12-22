package com.chen.study.concurrent.concurrent3.collection.blocking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 陈添明
 * @date 2018/11/25
 */
public class ArrayBlockingQueueExample {


    /**
     * 1. 先进先出
     * 2. 有边界
     * 3. 内部使用数组实现
     */
    private ArrayBlockingQueue<String> queue;

    @Before
    public void create(){
        queue = new ArrayBlockingQueue<>(2);
    }


    /**
     * add():
     * 插入元素到队尾, 成功返回true
     * 如果队列已满，抛出异常：IllegalStateException: Queue full
     */
    @Test
    public void testAdd(){
        queue.add("1");
        queue.add("2");
        queue.add("3");
        System.out.println(queue);
    }

    /**
     * offer():
     * 插入元素到队尾, 成功返回true。
     * 如果队列已满，插入失败，返回false。
     */
    @Test
    public void offer(){
        System.out.println(queue.offer("1"));
        System.out.println(queue.offer("2"));
        System.out.println(queue.offer("3"));
        System.out.println(queue);
    }

    /**
     * put():
     * 插入元素到队尾.
     * 如果队列已满，则会阻塞等待。
     */
    @Test
    public void put() throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(1_000);
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put("a");
        queue.put("b");
        queue.put("c");
        System.out.println(queue);
    }


    /**
     * poll():
     * 移除队首元素并返回。
     * 如果队列为空，则返回null
     * @throws InterruptedException
     */
    @Test
    public void poll() throws InterruptedException {
        queue.put("a");
        queue.put("b");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println("=========");
    }

    /**
     * remove():
     * 移除队首元素并返回。
     * 如果队列为空，抛出异常：NoSuchElementException
     * @throws InterruptedException
     */
    @Test
    public void remove() throws InterruptedException {
//        queue.put("a");
//        queue.put("b");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue);
        System.out.println("=========");
    }

    /**
     * take():
     * 移除队首元素并返回。
     * 如果队列为空，阻塞等待
     * @throws InterruptedException
     */
    @Test
    public void take() throws InterruptedException {
        queue.put("a");
        queue.put("b");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue);
        System.out.println("=========");
    }


    /**
     * peek():
     * 获取队首元素
     * 如果队列为空，则返回null
     * @throws InterruptedException
     */
    @Test
    public void peek() throws InterruptedException {
        queue.put("a");
        queue.put("b");
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println("=========");
    }


    /**
     * element():
     * 获取队首元素，
     * 如果队列为空，则抛出异常-NoSuchElementException
     * @throws InterruptedException
     */
    @Test
    public void element() throws InterruptedException {
//        queue.put("a");
//        queue.put("b");
        System.out.println(queue.element());
        System.out.println(queue.element());
        System.out.println(queue);
        System.out.println("=========");
    }



    /**
     * drainTo():
     * 排干队列元素到指定集合中去
     * @throws InterruptedException
     */
    @Test
    public void drainTo() throws InterruptedException {
        queue.put("a");
        queue.put("b");
        ArrayList<String> list = new ArrayList<>();
        queue.drainTo(list);
        System.out.println(queue);
        System.out.println(list);
        System.out.println("=========");
    }












}
