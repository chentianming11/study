package com.chen.study.concurrent.concurrent3.juc.util.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ConditionNotify implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try{
            lock.lock();//获得了锁.
            System.out.println("begin - conditionNotify");
            condition.signal();//唤醒阻塞状态的线程

            Thread.sleep(2_000);
            //if(uncondition){
//                condition.await();
            // }
            //condition.notify;
//            condition.await();
            System.out.println("end - conditionNotify");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //释放锁
        }
    }
}
