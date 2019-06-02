package com.chen.study.classloader.c5;

/**
 * @author 陈添明
 * @date 2018/10/14
 */
public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.study.demo.classloader.c5.SimpleClass");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
    }
}
