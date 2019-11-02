package com.chen.study.spring.boot.importdemo.current;

import com.chen.study.spring.boot.importdemo.other.OtherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
@Configuration
// 使用@Import导入其他配置类
@Import(OtherConfig.class)
public class CurrentConfig {

    @Bean
    public Current current() {
        return new Current();
    }

}
