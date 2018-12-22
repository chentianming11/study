package com.chen.study.concurrent.concurrent2.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 始终以当前线程作为key
 * @author 陈添明
 * @date 2018/9/23
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread thread = Thread.currentThread();
            storage.put(thread, t);
        }
    }

    public T get(){
        synchronized (this){
            Thread thread = Thread.currentThread();
            T value = storage.get(thread);
            if (value == null) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue(){
        return null;
    }



}
