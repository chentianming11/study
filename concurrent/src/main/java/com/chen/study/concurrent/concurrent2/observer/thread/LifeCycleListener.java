package com.chen.study.concurrent.concurrent2.observer.thread;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent event);
}
