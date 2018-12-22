package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * @author 陈添明
 * @date 2018/10/1
 */
public class SchedulerThread extends Thread {

    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }


    public void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true){
            activationQueue.take().execute();
        }
    }
}
