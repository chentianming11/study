package com.chen.study.zookeeper.curator.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 陈添明
 * @date 2018/12/14
 */
public class NodeCacheTest {

    private final static String connectionInfo = "127.0.0.1:2181";

    private static final String PATH = "/example/nodeCache";

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

        // 创建NodeCache
        NodeCache nodeCache = new NodeCache(client, PATH);
        // 必须要先start
        nodeCache.start();

        NodeCacheListener listener = () -> {
            System.out.println("节点变化");
            ChildData currentData = nodeCache.getCurrentData();
            if (currentData != null) {
                System.out.println("节点数据：" + currentData);
            }else {
                System.out.println("节点无数据");
            }
        };
        nodeCache.getListenable().addListener(listener);

        Thread.currentThread().join();


    }
}
