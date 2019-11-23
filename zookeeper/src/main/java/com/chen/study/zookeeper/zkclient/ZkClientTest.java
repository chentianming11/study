package com.chen.study.zookeeper.zkclient;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author 陈添明
 * @date 2019/11/23
 */
public class ZkClientTest {

    public static void main(String[] args) throws Exception {
        // 实例化ZooKeeper客户端
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181", 4000,
                event -> System.out.println("event.type" + event.getType()));
        //创建节点
        zookeeper.create("/watch", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //注册监听
        zookeeper.exists("/watch", true);
        Thread.sleep(1000);
        //修改节点的值触发监听
        zookeeper.setData("/watch", "1".getBytes(), -1);
        System.in.read();
    }
}
