package com.chen.study.classloader.c3;

import java.io.*;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class MyClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "/Users/mac/work/git/demo/src/test/java/";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;

    }

    public MyClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }
}
