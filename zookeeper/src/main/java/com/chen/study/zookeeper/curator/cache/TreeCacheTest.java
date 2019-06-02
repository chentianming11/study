package com.chen.study.zookeeper.curator.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 陈添明
 * @date 2018/12/14
 */
public class TreeCacheTest {

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


        TreeCache treeCache = new TreeCache(client, PATH);
        treeCache.start();

        treeCache.getListenable().addListener((client1, event) -> {
            TreeCacheEvent.Type type = event.getType();
            ChildData data = event.getData();
            System.out.println("事件类型：" + type + ", 节点数据：" + data);
        });

        Thread.currentThread().join();

    }
}
