package com.chen.spring.action.c4.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Aspect
@Component
public class Audience2 {

    @Pointcut("execution(* com.chen.spring.action.c4.aop.Performance.perform(..))")
    public void perform() {
    }


    @Around("perform()")
    public void watchPerformance(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("手机静音");
        System.out.println("就坐");
        try {
            proceedingJoinPoint.proceed();
            System.out.println("鼓掌");
        } catch (Throwable throwable) {
            System.out.println("退款");
        }
    }
}
