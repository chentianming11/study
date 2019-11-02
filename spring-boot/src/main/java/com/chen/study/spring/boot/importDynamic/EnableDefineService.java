package com.chen.study.spring.boot.importDynamic;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class, LoggerDefinitionRegistrar.class}) //
public @interface EnableDefineService {

    //配置一些方法
    Class<?>[] exclude() default {};
}
