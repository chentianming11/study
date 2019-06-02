package com.chen.study.classloader.c3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1. 类加载器的委托是优先交给父加载器先尝试加载、
 * 2. 父加载器和子加载器其实是一种包装关系，或者包含关系。
 * @author 陈添明
 * @date 2018/10/13
 */
public class MyClassLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader-1");
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader-2");
        Class<?> aClass = myClassLoader.loadClass("com.study.demo.classloader.c3.MyObject");
        Class<?> aClass2 = myClassLoader2.loadClass("com.study.demo.classloader.c3.MyObject");
        System.out.println(aClass.hashCode());
        System.out.println(aClass2.hashCode());

        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello", new Class[]{});
        Object invoke = hello.invoke(o, new Object[]{});
        System.out.println(invoke);

    }
}
