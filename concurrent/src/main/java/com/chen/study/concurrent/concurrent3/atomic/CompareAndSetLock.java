package com.chen.study.concurrent.concurrent3.atomic;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean b = value.compareAndSet(0, 1);
        if (!b) {
            throw new GetLockException("获取锁失败");
        }else {
            System.out.println(Thread.currentThread().getName() + "--> 获得锁");
            lockedThread = Thread.currentThread();
        }

    }

    public void unlock(){
        if (0 == value.get()){
            return;
        }
        if (Objects.equals(lockedThread, Thread.currentThread())){
            value.compareAndSet(1, 0);
        }
    }
}
