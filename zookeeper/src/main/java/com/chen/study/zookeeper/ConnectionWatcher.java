package com.chen.study.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 链接监视器
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT=5000;
    protected ZooKeeper zk;

    CountDownLatch connectedSignal=new CountDownLatch(1);
    public void connect(String host) throws IOException, InterruptedException{
        zk=new ZooKeeper(host, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState()== Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    public void close() throws InterruptedException{
        zk.close();
    }

}