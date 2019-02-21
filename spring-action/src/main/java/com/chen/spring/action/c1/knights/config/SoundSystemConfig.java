package com.chen.spring.action.c1.knights.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.chen",
               excludeFilters = { @Filter(Configuration.class) })
public class SoundSystemConfig {
}
