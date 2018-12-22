package com.chen.study.concurrent.concurrent3.atomic;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class AtomicIntegerArrayTest {

    private int[] ints = new int[10];

    private AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);


    @Test
    public void testCreate(){
        AtomicIntegerArray a = new AtomicIntegerArray(10);
        System.out.println(a.length());

    }


    @Test
    public void testAtomicArray() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (atomicIntegerArray.get(0) != 10) {

            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicIntegerArray.set(i, 10);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void testArray() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (ints[0] != 10) {

            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ints[i] = 10;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
