package com.chen.study.concurrent.concurrent3.juc.util.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 可循环篱笆
 * @author 陈添明
 * @date 2018/11/6
 */
public class CyclicBarrierExample1 {


    /**
     * 简单使用
     * @throws InterruptedException
     * @throws BrokenBarrierException
     */
    @Test
    public void test1() throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(() -> {
            try {

                Thread.sleep(5_000);
                System.out.println("T1执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(2_000);
                System.out.println("T1任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10_000);
                System.out.println("T2执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(3_000);
                System.out.println("T2任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("主线程执行到同步点");
        cyclicBarrier.await();
        System.out.println("主线程任务完成");
        Thread.currentThread().join();
    }

    /**
     * 在构造的时候，可以传入一个runnable，等所有子任务都执行到同步点时，回调执行该runnable
     */
    @Test
    public void test2() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("所有任务都执行到同步点了。。。。");
        });
        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("T1执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(2_000);
                System.out.println("T1任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(7_000);
                System.out.println("T2执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(3_000);
                System.out.println("T2任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.currentThread().join();
    }


    /**
     * 其他api
     */
    @Test
    public void test3() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2 );
        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("T1执行到同步点，等待其他线程都执行到同步点再执行后续任务");

                cyclicBarrier.await();
                Thread.sleep(2_000);
                System.out.println("T1任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(7_000);
                System.out.println("T2执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(3_000);
                System.out.println("T2任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        // 获取正在waiting的任务数
        System.out.println(cyclicBarrier.getNumberWaiting());
        // 任务份数
        System.out.println(cyclicBarrier.getParties());
        // 同步屏障是否被打断
        System.out.println(cyclicBarrier.isBroken());

        Thread.sleep(5_000);

        // 获取正在waiting的任务数
        System.out.println(cyclicBarrier.getNumberWaiting());
        // 任务份数
        System.out.println(cyclicBarrier.getParties());
        // 同步屏障是否被打断
        System.out.println(cyclicBarrier.isBroken());


        Thread.sleep(10_000);

        // 获取正在waiting的任务数
        System.out.println(cyclicBarrier.getNumberWaiting());
        // 任务份数
        System.out.println(cyclicBarrier.getParties());
        // 同步屏障是否被打断
        System.out.println(cyclicBarrier.isBroken());

        Thread.currentThread().join();
    }


    /**
     * broken  -- 打断
     * reset  -- 重置
     * 当cyclicBarrier中有任务处于wait，调用reset就会导致 BrokenBarrierException 异常
     */
    @Test
    public void test4() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("T1执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(1_000);
                System.out.println("T1任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5_000);
                System.out.println("T2执行到同步点，等待其他线程都执行到同步点再执行后续任务");
                cyclicBarrier.await();
                Thread.sleep(2_000);
                System.out.println("T2任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(4_000);
        // 获取正在waiting的任务数
        System.out.println(cyclicBarrier.getNumberWaiting());
        // 任务份数
        System.out.println(cyclicBarrier.getParties());
        // 同步屏障是否被打断
        System.out.println(cyclicBarrier.isBroken());
        cyclicBarrier.reset();
        System.out.println("-----------------调用reset------------");
        // 获取正在waiting的任务数
        System.out.println(cyclicBarrier.getNumberWaiting());
        // 任务份数
        System.out.println(cyclicBarrier.getParties());
        // 同步屏障是否被打断
        System.out.println(cyclicBarrier.isBroken());
        Thread.currentThread().join();

    }


    /**
     * 1. CountDownLatch不能reset，而cyclicBarrier是可以循环使用的
     * 2. CountDownLatch是工作线程之间互不关心。
     * 3. cyclicBarrier是工作线程是都等到同步点，然后再执行剩下的任务。
     */

    class MyCountDownLatch extends CountDownLatch {

       private final Runnable runnable;
        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0){
                runnable.run();
            }
        }
    }


    @Test
    public void test5() throws InterruptedException {
        MyCountDownLatch latch = new MyCountDownLatch(2, () -> {
            System.out.println("所有任务都执行完成了");
        });

        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("T1任务执行完成");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5_000);
                System.out.println("T2任务执行完成");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.currentThread().join();
    }
}
