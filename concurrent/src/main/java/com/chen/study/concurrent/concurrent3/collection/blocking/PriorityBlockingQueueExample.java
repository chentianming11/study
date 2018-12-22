package com.chen.study.concurrent.concurrent3.collection.blocking;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author 陈添明
 * @date 2018/11/25
 */
public class PriorityBlockingQueueExample {

    /**
     * 1. 先进先出
     * 2. 无边界，可以设置初始容量，容量不足，再插入数据会扩容。
     * 3. 优先级排序
     */
    private PriorityBlockingQueue queue;

    @Before
    public void setup(){
        queue = new PriorityBlockingQueue(3);
    }


    /**
     * put() add()  offer()完全等价
     * 向队列中插入一个元素
     */
    @Test
    public void add(){
        System.out.println(queue.add("1"));
        System.out.println(queue.add("2"));
        System.out.println(queue.add("3"));

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
        queue.put("a");
        queue.put("b");
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
//        queue.put("a");
//        queue.put("b");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue);
        System.out.println("=========");
    }


    /**
     * peek():
     * 获取队首元素
     * 如果队列为空，则返回null
     */
    @Test
    public void peek()  {
//        queue.put("a");
//        queue.put("b");
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
     * 实现了Comparable接口的对象可以直接插入到队列中
     * 否则，应该在创建队列的时候手动传入一个比较器
     */
    @Test
    public void UserWithNoCompare(){
        PriorityBlockingQueue<UserWithNoCompare> queue = new PriorityBlockingQueue<>(2, (Comparator.comparingInt(UserWithNoCompare::hashCode)));
        queue.add(new UserWithNoCompare());
        queue.add(new UserWithNoCompare());
        queue.add(new UserWithNoCompare());
        System.out.println(queue);

    }

    /**
     * 实现了Comparable接口的对象可以直接插入到队列中
     * 否则，应该在创建队列的时候手动传入一个比较器
     */
    @Test
    public void UserWithCompare(){
        queue.add(new UserWithCompare());
        queue.add(new UserWithCompare());
        System.out.println(queue);

    }




    static class UserWithNoCompare {

    }


    static class UserWithCompare implements Comparable {

        @Override
        public int compareTo(Object o) {
            return this.hashCode() - o.hashCode();
        }
    }


}
