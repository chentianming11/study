package com.chen.study.spring.boot.importdemo.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
@Configuration
public class OtherConfig {

    @Bean
    public Other other() {
        return new Other();
    }

}
