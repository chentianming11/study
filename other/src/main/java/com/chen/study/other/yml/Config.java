package com.chen.study.other.yml;

import lombok.Data;

import java.util.Map;

/**
 * @author 陈添明
 * @date 2019/9/18
 */
@Data
public class Config {

    public static final String PREFIX = "spring.retrofit";

    private Map<String, PoolConfig> pool;

    private boolean enableBodyCallAdapter = true;


    private boolean enableResponseCallAdapter = true;

    private boolean enableFastJsonConverter = true;

}
