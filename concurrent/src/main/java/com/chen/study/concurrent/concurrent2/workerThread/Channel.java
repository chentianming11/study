package com.chen.study.concurrent.concurrent2.workerThread;


/**
 * 传送带
 * @author 陈添明
 * @date 2018/9/26
 */
public class Channel {

    private final static int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int head;
    private int tail;
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers){
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < this.workerPool.length; i++) {
            workerPool[i] = new WorkerThread("worker-" + i, this);
        }
    }

    public void startWorker(){
        for (WorkerThread workerThread : this.workerPool) {
            workerThread.start();
        }
    }

    public synchronized void put(Request request){
        while (count >= requestQueue.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take(){
        while (count <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
        Request request = this.requestQueue[head];
        this.head = (head + 1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }

}
