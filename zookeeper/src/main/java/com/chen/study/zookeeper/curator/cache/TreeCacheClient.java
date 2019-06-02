package com.chen.study.zookeeper.curator.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author 陈添明
 * @date 2018/12/14
 */
public class TreeCacheClient {


    private final static String connectionInfo = "127.0.0.1:2181";

    private static final String PATH = "/example/treeCache";

    public static void main(String[] args) throws Exception {
        // 创建curator客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectionInfo)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();

        // 增删改查
        client.create()
                .creatingParentsIfNeeded()
                .forPath(PATH + "/a", "a".getBytes());


        client.create().creatingParentsIfNeeded()
                .forPath(PATH + "/b", "b".getBytes());


        client.create().creatingParentsIfNeeded()
                .forPath(PATH + "/c", "c".getBytes());


        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH + "/a/b", "a".getBytes());


        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH + "/b/c", "b".getBytes());


        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH + "/c/d", "c".getBytes());


        client.setData().forPath(PATH + "/c", "my".getBytes());

        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(PATH+"/b");

    }
}
