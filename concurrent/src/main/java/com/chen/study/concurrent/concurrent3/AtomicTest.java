package com.chen.study.concurrent.concurrent3;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 陈添明
 * @date 2018/11/3
 */
public class AtomicTest {

    /**
     * CAS
     *  1. 可见性
     *  2. 有序性
     *  3. 原子性
     *
     *  1. volatile修饰的变量，能保证前2者
     *  2. CAS算法，也就是cpu级别的同步指令，相当于乐观锁，它可以探测到其它线程对共享数据的变化情况
     *
     *
     *  CAS轻量级锁带来的严重问题：ABA问题
     *
     */

    private static AtomicInteger value = new AtomicInteger();

//    private volatile static  int value = 0;


    public static void main(String[] args) {



        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(500);
//                        System.out.println(Thread.currentThread().getName() + ": " + value++);
                        System.out.println(Thread.currentThread().getName() + ": " + value.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "线程-" + i).start();
        }
    }


    @Test
    public void testCreate(){
        // 创建  默认0
        AtomicInteger atomicInteger = new AtomicInteger();
        // 创建   指定默认值10
        System.out.println(atomicInteger);
        System.out.println(new AtomicInteger(10));
    }

    @Test
    public void testSet(){
        AtomicInteger i = new AtomicInteger();
        // 赋值
        i.set(100);
        System.out.println(i);
        // 延时赋值 ，使用的时候生效
        i.lazySet(1000);
        System.out.println(i);

        // 先get再自增
        int v = i.getAndIncrement();
        System.out.println(v + ":" + i);

        // get and add
        AtomicInteger i3 = new AtomicInteger(10);
        int andAdd = i3.getAndAdd(10);
        System.out.println(andAdd + ":" + i3);
    }


    @Test
    public void testCAS(){
        AtomicInteger i = new AtomicInteger(10);
        boolean b = i.compareAndSet(12, 20);
        System.out.println(b);
        System.out.println(i);
    }

}
