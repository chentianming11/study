package com.chen.study.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;

/**
 * 高级特性测试
 * @author 陈添明
 * @date 2018/12/14
 */
public class CuratorRecipesTest {


    private final static String connectionInfo = "127.0.0.1:2181";
    CuratorFramework client;

    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(connectionInfo)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();
    }



}
