package com.chen.study.web.test;

import com.chen.study.web.entity.Config;
import com.chen.study.web.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 广播表的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.chen.study.web.mapper")
public class ConfigShardingTest {
    @Autowired
    ConfigService configService;

    @Test
    public void insert() {

        configService.insert();
    }

    @Test
    public void update() {

        configService.update(1);
    }

    @Test
    public void select() {
        Config config1 = configService.geConfigById(1);
        System.out.println("------config1:" + config1);

        Config config2 = configService.geConfigById(2);
        System.out.println("------config2:" + config2);
    }

}

