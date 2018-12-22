package com.chen.study.concurrent.concurrent3.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 陈添明
 * @date 2018/11/10
 */
public class AtomicIntegerFieldTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe testMe = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {

                final  int max = 20;
                for (int i1 = 0; i1 < max; i1++) {
                    int result = updater.getAndIncrement(testMe);
                    System.out.println(Thread.currentThread().getName() + ": " + result);

                }
            }).start();
        }
    }

    /**
     * 1. 想让类的属性操作具备原子性：
     * 1.1 volatile
     * 1.2 必须有访问权限
     * 1.3 类型必须一致
     * 1.4 其他
     *
     *  2. 不想使用锁
     */
    static class TestMe {
        volatile int i;
    }
}
