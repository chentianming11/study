package com.chen.study.concurrent.concurrent3.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class AtomicBooleanTest {

    @Test
    public void testCreate(){
        // 默认false
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean);
        // 指定值
        AtomicBoolean t = new AtomicBoolean(true);
        System.out.println(t);
    }


    @Test
    public void testGetAndSet(){
        AtomicBoolean t = new AtomicBoolean(true);
        boolean r = t.getAndSet(false);
        System.out.println(r + ": " + t);
    }
}
