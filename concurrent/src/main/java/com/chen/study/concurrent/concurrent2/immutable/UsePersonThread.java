package com.chen.study.concurrent.concurrent2.immutable;

/**
 * @author 陈添明
 * @date 2018/9/19
 */
public class UsePersonThread extends Thread {

    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " : " + person);
        }
    }
}
