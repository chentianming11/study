package com.chen.study.concurrent.concurrent2.countdown;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class CountDown {

    private int counter;

    public CountDown(int counter) {
        this.counter = counter;
    }

    public void countDown() {
        synchronized (this) {
            this.counter--;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != 0) {
                this.wait();
            }
        }
    }
}
