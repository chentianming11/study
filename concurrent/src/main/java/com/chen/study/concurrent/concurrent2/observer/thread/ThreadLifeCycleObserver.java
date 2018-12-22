package com.chen.study.concurrent.concurrent2.observer.thread;

import com.study.demo.util.Is;

import java.util.List;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private  final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if (Is.empty(ids)){
            return;
        }

        ids.forEach(id -> {
            new Thread(new ObserverRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id " + id);
                        Thread.sleep(1000L);

                        notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                    }

                }
            }, id).start();
        });
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is " + event.getState());

            if (event.getCause() != null){
                System.out.println("The runnable [" + event.getThread().getName() + "] failed ");
            }
        }
    }
}
