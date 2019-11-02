package com.chen.study.spring.boot.importDynamic;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 使用 #{@link ImportBeanDefinitionRegistrar} 实现动态装配
 *
 * @author 陈添明
 * @date 2019/11/2
 */
public class LoggerDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(EnableDefineService.class.getName());
        // 拿到注解属性，做一些逻辑处理


        // 将需要装配的beanClass注解到 BeanDefinitionRegistry
        Class beanClass = LoggerService.class;
        RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
        String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
