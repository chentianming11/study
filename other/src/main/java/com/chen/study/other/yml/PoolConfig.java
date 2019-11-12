package com.chen.study.other.yml;

import lombok.Data;

/**
 * 连接池参数配置
 */
@Data
public class PoolConfig {

    /**
     * 最大空闲连接
     */
    private int maxIdleConnections = 5;

    /**
     * 保活时间，单位为秒
     */
    private long keepAliveSecond = 300;
}