package com.chen.spring.action.c3.valueinject.soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {
    @Autowired
    Environment environment;

    @Value("disc.title")
    private String title;

    @Value("disc.artist")
    private String artist;

    @Bean
    public BlankDisc blankDisc(){
        BlankDisc blankDisc = new BlankDisc(environment.getProperty("disc.title"),
                environment.getProperty("disc.artist"));
        return blankDisc;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
