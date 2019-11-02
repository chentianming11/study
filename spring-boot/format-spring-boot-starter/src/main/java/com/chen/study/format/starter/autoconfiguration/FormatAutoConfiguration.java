package com.chen.study.format.starter.autoconfiguration;


import com.chen.study.format.starter.format.FormatProcessor;
import com.chen.study.format.starter.format.JsonFormatProcessor;
import com.chen.study.format.starter.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FormatAutoConfiguration {

    /**
     *
     * @return
     *
     */

    //metadata-auto....
    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Bean
    @Primary
    public FormatProcessor stringFormat(){
        return new StringFormatProcessor();
    }

    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public FormatProcessor jsonFormat(){
        return new JsonFormatProcessor();
    }

}
