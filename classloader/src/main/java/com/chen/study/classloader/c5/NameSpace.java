package com.chen.study.classloader.c5;

/**
 * @author 陈添明
 * @date 2018/10/27
 */
public class NameSpace {


    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("java.lang.String");
        Class<?> bClass = classLoader.loadClass("java.lang.String");

        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
    }
}
