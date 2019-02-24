package com.chen.spring.action.c3.ambiguity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Configuration
public class DessertConfig {

    @Bean
    @Primary
    public IceCream iceCream(){
        return new IceCream();
    }
}
