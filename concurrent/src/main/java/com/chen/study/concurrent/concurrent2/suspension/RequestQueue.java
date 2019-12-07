package com.chen.study.concurrent.concurrent2.suspension;


import java.util.LinkedList;

/**
 * @author 陈添明
 * @date 2018/9/22
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest(){
        synchronized (queue){
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }

            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request){
        synchronized (queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
