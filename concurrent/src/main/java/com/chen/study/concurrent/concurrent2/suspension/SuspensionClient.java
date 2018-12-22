package com.chen.study.concurrent.concurrent2.suspension;

/**
 * 挂起模式
 * @author 陈添明
 * @date 2018/9/22
 */
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "chen").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(20000);
        serverThread.close();
    }
}
