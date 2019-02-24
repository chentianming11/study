package com.chen.spring.action.c4.aop.advice;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Aspect
@Component
public class Audience {

    @Pointcut("execution(* com.chen.spring.action.c4.aop.Performance.perform(..))")
    public void perform(){}

    @Before("perform()")
    public void silenceCellphone(){
        System.out.println("手机静音");
    }

    @Before("perform()")
    public void takeSeats(){
        System.out.println("就坐");
    }

    @AfterReturning("perform()")
    public void applause(){
        System.out.println("鼓掌");
    }

    @AfterThrowing("perform()")
    public void demandRefund(){
        System.out.println("退款");
    }
}
