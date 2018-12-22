package com.chen.study.concurrent.concurrent3.atomic;

/**
 * @author 陈添明
 * @date 2018/11/3
 */
public class AtomicIntegerTest {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething();
                } catch (GetLockException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程-" + i).start();
        }



    }


    private static void doSomething() throws GetLockException, InterruptedException {
        try {
            lock.tryLock();

            while (true){
                Thread.sleep(1_000);
            }

        }finally {
            lock.unlock();
        }

    }
}
