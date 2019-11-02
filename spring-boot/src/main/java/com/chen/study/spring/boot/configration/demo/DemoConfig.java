package com.chen.study.spring.boot.configration.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用Configuration的形式，装配一个bean
 *
 * @author 陈添明
 * @date 2019/11/2
 */
@Configuration
public class DemoConfig {

    @Bean
    public Demo demo() {
        return new Demo();
    }
}
