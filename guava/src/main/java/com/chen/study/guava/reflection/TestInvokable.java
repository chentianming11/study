package com.chen.study.guava.reflection;

import com.google.common.reflect.Invokable;
import org.junit.Test;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by chen on 2018/3/4.
 */
public class TestInvokable {

    @Test
    public void test1() throws NoSuchMethodException {
        // Method对象
        Method description = Person.class.getMethod("getDescription");
        // Constructor对象
        Constructor constructor = Person.class.getConstructor();

        // 使用Invokable包装Method或者Constructor
        Invokable<?, Object> invokable = Invokable.from(description);

        // 判断方法是不是public
        System.out.println(invokable.isPublic());

        // 方法是否能够被子类重写？
        System.out.println(invokable.isOverridable());

        // 方法的第一个参数是否被定义了注解@Nullable？
        invokable.getParameters().get(0).isAnnotationPresent(Nullable.class);

    }
}
