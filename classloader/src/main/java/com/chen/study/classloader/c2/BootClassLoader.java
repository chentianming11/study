package com.chen.study.classloader.c2;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class BootClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        // 根加载加载的路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展加载器
        System.out.println(System.getProperty("java.ext.dirs"));

        Class<?> aClass = Class.forName("com.study.demo.classloader.c2.SimpleObject");
        System.out.println(aClass.getClassLoader()); // AppClassLoader
        System.out.println(aClass.getClassLoader().getParent()); // ExtClassLoader
        System.out.println(aClass.getClassLoader().getParent().getParent());

    }
}
