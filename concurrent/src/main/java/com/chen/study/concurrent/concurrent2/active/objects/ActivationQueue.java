package com.chen.study.concurrent.concurrent2.active.objects;

import java.util.LinkedList;

/**
 * @author 陈添明
 * @date 2018/10/1
 */
public class ActivationQueue {

    private static final  int MAX = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request){
        while(methodQueue.size() >= MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while (methodQueue.size() <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
