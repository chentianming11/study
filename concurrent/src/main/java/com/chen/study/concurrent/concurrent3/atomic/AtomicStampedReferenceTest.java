package com.chen.study.concurrent.concurrent3.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                int stamp = atomicStampedReference.getStamp();
                atomicStampedReference.compareAndSet(100, 101,stamp , stamp + 1);

                int stamp2 = atomicStampedReference.getStamp();
                atomicStampedReference.compareAndSet(101, 100,stamp2 , stamp2 + 1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("before sleep stamp=" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean b = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
