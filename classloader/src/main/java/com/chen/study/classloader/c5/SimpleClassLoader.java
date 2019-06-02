package com.chen.study.classloader.c5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class SimpleClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "/Users/mac/work/git/demo/src/test/java/";

    private String dir = DEFAULT_DIR;


    public SimpleClassLoader() {
        super();

    }

    public SimpleClassLoader(ClassLoader parent) {
        super(parent);
    }


    /**
     * @param name 类的全路径名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replaceAll("\\.", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("类没有找到：" + name);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("类加载失败：" + name);

        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileInputStream fileInputStream = new FileInputStream(classFile);
        ) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buf)) != -1){
                byteArrayOutputStream.write(buf, 0, len);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // 先尝试自己加载
                try {
                    Class<?> aClass = this.findClass(name);
                    if (aClass != null) {
                        if (resolve) {
                            resolveClass(aClass);
                        }
                        return aClass;
                    }else {
                        // 交由系统加载器按委托机制进行加载
                        Class<?> loadClass = loadBySystemClassLoader(name, resolve);
                        return loadClass;
                    }
                } catch (ClassNotFoundException e) {
                    Class<?> loadClass = loadBySystemClassLoader(name, resolve);
                    return loadClass;
                }

            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private Class<?> loadBySystemClassLoader(String name, boolean resolve) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = systemClassLoader.loadClass(name);
        if (resolve) {
            resolveClass(loadClass);
        }
        return loadClass;
    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
