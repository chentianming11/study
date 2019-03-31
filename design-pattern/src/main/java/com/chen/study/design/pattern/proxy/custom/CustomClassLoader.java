package com.chen.study.design.pattern.proxy.custom;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 陈添明
 * @date 2019/3/31
 */
public class CustomClassLoader extends ClassLoader {


    private File classPathFile;
    public CustomClassLoader(){
        String classPath = CustomClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = new File(classPathFile,name.replaceAll("\\.","/") + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("类没有找到：" + name);
        }
        try {
            byte[] classBytes = FileUtils.readFileToByteArray(classFile);
            if (null == classBytes || classBytes.length == 0) {
                throw new ClassNotFoundException("类加载失败：" + name);
            }
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }

    }

    public static void main(String[] args) {

        Class<CustomClassLoader> loaderClass = CustomClassLoader.class;

        // com.chen.study.design.pattern.proxy.custom.CustomClassLoader
        System.out.println(loaderClass.getName());

        // CustomClassLoader
        System.out.println(loaderClass.getSimpleName());

        // com.chen.study.design.pattern.proxy.custom.CustomClassLoader
        System.out.println(loaderClass.getCanonicalName());

        // com.chen.study.design.pattern.proxy.custom.CustomClassLoader
        System.out.println(loaderClass.getTypeName());

        // com.chen.study.design.pattern.proxy.custom
        System.out.println(loaderClass.getPackage().getName());
    }

}
