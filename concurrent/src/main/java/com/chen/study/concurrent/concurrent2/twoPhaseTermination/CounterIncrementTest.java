package com.chen.study.concurrent.concurrent2.twoPhaseTermination;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class CounterIncrementTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10000);

        counterIncrement.close();
    }
}
