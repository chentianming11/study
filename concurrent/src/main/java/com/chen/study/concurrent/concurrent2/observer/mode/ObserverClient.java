package com.chen.study.concurrent.concurrent2.observer.mode;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class ObserverClient {

    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer binaryObserver = new BinaryObserver(subject);
        Observer octalObserver = new OctalObserver(subject);

        System.out.println("--------------------------");
        subject.setState(10);

        System.out.println("--------------------------");
        subject.setState(100);
    }
}
