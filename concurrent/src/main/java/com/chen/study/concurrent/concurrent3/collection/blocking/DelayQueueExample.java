package com.chen.study.concurrent.concurrent3.collection.blocking;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/25
 */
public class DelayQueueExample {


    /**
     * 1. 延时队列中的元素会根据过期时间排序吗？
     *      1.1 排序规则取决于对象compareTo()方法的实现，当然可以实现为按过期时间排序。
     * 2. 队列为空时，使用poll或者take方法返回值是什么？
     *      2.1 poll返回null，take阻塞等待
     * 3. 元素已经过期，执行take时候会立即返回吗？
     *      3.1 会立即返回
     * 4. 未过期的元素，无法remove？
     *      4.1 是的
     * 5. 不接受null
     *
     * 6. 通过迭代器是否可以立即返回未过期的元素
     *      6.1  是的
     * 7. 队列中的元素必须实现Delayed接口
     *
     * 8. 无边界队列
     */

    private DelayQueue queue;

    @Before
    public void setup(){
        queue = new DelayQueue();
    }


    @Test
    public void test() throws InterruptedException {

        /**
         * 一个线程，不停的消费
         */
        CompletableFuture.runAsync(() -> {
            for (;;){
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        /**
         * 先输出2， 然后输出1
         */
        queue.put(new DelayElement<>("1", 10_000));
        Thread.sleep(1_000);
        queue.put(new DelayElement<>("2", 1_000));

        Thread.currentThread().join();
    }


    /**
     * 因为是无边界队列，所以 add put offer行为一致
     */
    @Test
    public void add() throws InterruptedException {
        queue.add(new DelayElement<>("d1", 2000));
        queue.put(new DelayElement<>("d1", 2000));
        queue.offer(new DelayElement<>("d1", 2000));

        System.out.println(queue);
        System.out.println(queue.size());
    }


    /**
     * peek()/element(): 获取队首的一个元素，为空时返回null。(与元素是否过期无关)
     */
    @Test
    public void peek() throws InterruptedException {
        queue.add(new DelayElement<>("d1", 2000));

        Thread.sleep(3_000);
//        System.out.println(queue.peek());
        System.out.println(queue.element());
        System.out.println("------");

        System.out.println(queue.size());
    }


    /**
     * remove()：当队列里面没有过期元素的时候，会抛出异常：NoSuchElementException
     * poll()：当队列里面没有过期元素的时候，会返回null,poll可以指定等待时间
     * take(): 当队列里面没有过期元素的时候，会阻塞等待
     * @throws InterruptedException
     */
    @Test
    public void remove() throws InterruptedException {
        queue.add(new DelayElement<>("d1", 2000));

//        Thread.sleep(3_000);
//        System.out.println(queue.remove());
        System.out.println(queue.poll());

        System.out.println(queue.size());
    }


    static class DelayElement<E> implements Delayed {

       private final E e;

       private final long expireTime;


        public DelayElement(E e, long delay) {
            this.e = e;
            this.expireTime = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {

            // diff > 0 :未过期
            long diff = expireTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayElement that = (DelayElement) o;
            if (this.expireTime < that.getExpireTime()){
                return -1;
            } else if (this.expireTime > that.getExpireTime()){
                return 1;
            } else {
                return 0;
            }
        }

        public E getData(){
            return e;
        }

        public long getExpireTime(){
            return expireTime;
        }

        @Override
        public String toString() {
            return String.format("value: %s, expire: %s", e.toString(), expireTime);
        }
    }




}
