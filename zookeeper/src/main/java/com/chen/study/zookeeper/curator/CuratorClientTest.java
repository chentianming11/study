package com.chen.study.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @author 陈添明
 * @date 2018/12/13
 */
public class CuratorClientTest {

    private final static String connectionInfo = "127.0.0.1:2181";

    /**
     * ﻿使用静态工程方法创建客户端
     */
    @Test
    public void create1(){
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectionInfo,5000,5000, retry);
    }


    /**
     * ﻿使用Fluent风格的Api创建会话
     */
    @Test
    public void create2(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(connectionInfo)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .build();

    }


    /**
     * 创建包含隔离命名空间的会话
     */
    @Test
    public void create3(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(connectionInfo)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .namespace("base")
                        .build();

    }




}
