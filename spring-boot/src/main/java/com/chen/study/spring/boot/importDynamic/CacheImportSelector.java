package com.chen.study.spring.boot.importDynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 使用 #{@link ImportSelector} 实现 bean动态装配
 *
 * @author 陈添明
 * @date 2019/11/2
 */
public class CacheImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(EnableDefineService.class.getName());
        // 拿到注解属性，做一些逻辑处理


        // 返回配置类的全路径
        return new String[]{CacheConfig.class.getName()};
    }

    /**
     * 比如，这是一个jar里面的配置类
     */
    @Configuration
    public static class CacheConfig {
        @Bean
        public CacheService cacheService() {
            return new CacheService();
        }
    }
}
