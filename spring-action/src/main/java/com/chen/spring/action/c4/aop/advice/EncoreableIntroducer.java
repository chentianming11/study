package com.chen.spring.action.c4.aop.advice;

import com.chen.spring.action.c4.aop.DefaultEncoreable;
import com.chen.spring.action.c4.aop.Encoreable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 允许返场引入器
 * @author 陈添明
 * @date 2019/2/24
 */
@Component
@Aspect
public class EncoreableIntroducer {

    @DeclareParents(value = "com.chen.spring.action.c4.aop.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
