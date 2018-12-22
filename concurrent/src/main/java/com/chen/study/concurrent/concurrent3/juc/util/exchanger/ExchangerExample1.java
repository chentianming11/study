package com.chen.study.concurrent.concurrent3.juc.util.exchanger;

import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 *
 * @author 陈添明
 * @date 2018/11/8
 */
public class ExchangerExample1 {

    /**
     * exchange(V v): v代表线程要交换的值
     * 返回值代表的是另一个线程交换给当前线程的值。
     *
     * 1. 如果另一个线程还没执行到交换点，那么当前线程就阻塞住
     * 2. 必须成对使用
     * @throws InterruptedException
     */

    @Test
    public void test1() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            try {
                String result = exchanger.exchange("来自于线程A的数据，哈哈哈");
                System.out.println(Thread.currentThread().getName() + "获得数据：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "线程A--").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            try {
                Thread.sleep(10_000);
                String result = exchanger.exchange("来自于线程B的数据，哇哇哇");
                System.out.println(Thread.currentThread().getName() + "获得数据：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "线程B--").start();

        Thread.currentThread().join();
    }


    /**
     * 1. 线程A发送的值是否和线程B接收的值是同一个（不仅仅是内容，而是只完全相同，就是一个东西）.
     */
    @Test
    public void test2() throws InterruptedException {
        Exchanger<Object> exchanger = new Exchanger<>();


        new Thread(() -> {
            Object o = new Object();
            System.out.println("线程A发送的东西" +  o);
            try {
                Object result = exchanger.exchange(o);
                System.out.println(Thread.currentThread().getName() + "获得数据：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "线程A--").start();

        new Thread(() -> {
            Object o = new Object();
            System.out.println("线程B发送的东西" +  o);
            try {
                Thread.sleep(10_000);
                Object result = exchanger.exchange(o);
                System.out.println(Thread.currentThread().getName() + "获得数据：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "线程B--").start();

        Thread.currentThread().join();
    }
}
