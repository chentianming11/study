package com.chen.study.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个config类，引用这个jar的应用并不会自动扫描到的啊。
 * 当然，更改@CompenentScan 也可以实现！！！
 * 但是这样做，还是动手做配置了啊！！！
 * 使用spring-boot的spi，将要不要自动装配这个Bean的控制交给jar包自身控制！！！
 * 具体就是 META-INFO下的配置文件
 * @author 陈添明
 * @date 2019/11/2
 */
@Configuration
public class DemoConfig {
    @Bean
    public DemoService demoService() {
        return new DemoService();
    }
}
