package com.chen.study.concurrent.concurrent2.read.write;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class ReaderWriterLockClient {

    public static void main(String[] args) {
        ShareData data = new ShareData(10);
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();

        new WriterWorker(data, "qrwerfsvasdfgs").start();
        new WriterWorker(data, "dhdhjweidgjsdfg").start();
    }
}
