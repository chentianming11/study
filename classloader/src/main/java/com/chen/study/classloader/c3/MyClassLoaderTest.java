package com.chen.study.classloader.c3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader");
        Class<?> aClass = myClassLoader.loadClass("com.study.demo.classloader.c3.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello", new Class[]{});
        Object invoke = hello.invoke(o, new Object[]{});
        System.out.println(invoke);

    }
}
