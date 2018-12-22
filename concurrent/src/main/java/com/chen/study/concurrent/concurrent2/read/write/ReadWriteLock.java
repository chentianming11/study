package com.chen.study.concurrent.concurrent2.read.write;

/**
 * 读写锁
 * @author 陈添明
 * @date 2018/9/16
 */
public class ReadWriteLock {

    /**
     * 正在读的Reader数量
     */
    private int readingReaders = 0;
    /**
     * 等待读的Reader数量
     */
    private int waitingReaders = 0;

    /**
     * 正在写的writer数量 <=1
     */
    private int wtritingWriters = 0;
    /**
     * 等待写的writer数量
     */
    private int waitingWriters = 0;


    /**
     * 读锁
     */
    public synchronized void readLock() throws InterruptedException {
        // 等待读的Reader数量 加1
        this.waitingReaders++ ;
        try {
            // 等待写的writer数量大于0  当前线程等待
            while (waitingWriters > 0){
                this.wait();
            }
            // 正在读  加1
            this.readingReaders++;

        } finally {
            // 等待读的Reader数量 减1
            this.waitingWriters--;

        }

    }

    /**
     * 放弃读锁
     */
    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }

    /**
     * 写锁
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || wtritingWriters > 0){
                this.wait();
            }

            this.wtritingWriters++;

        }finally {

            this.waitingWriters--;

        }
    }

    /**
     * 放弃写
     */
    public synchronized void writeUnlock(){
        this.wtritingWriters--;
        this.notifyAll();
    }

}
