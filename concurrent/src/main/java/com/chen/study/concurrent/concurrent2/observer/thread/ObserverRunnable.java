package com.chen.study.concurrent.concurrent2.observer.thread;

import lombok.Data;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public abstract class ObserverRunnable implements Runnable{

    protected LifeCycleListener listener;

    public ObserverRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(RunnableEvent event){
        listener.onEvent(event);
    }

    public enum RunnableState {
        RUNNING, ERROR, DONE
    }

    @Data
    public static class RunnableEvent {
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }
    }
}
