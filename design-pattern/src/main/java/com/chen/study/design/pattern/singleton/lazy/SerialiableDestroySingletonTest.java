package com.chen.study.design.pattern.singleton.lazy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * 序列化破坏单例测试
 * @author 陈添明
 * @date 2019/3/27
 */
public class SerialiableDestroySingletonTest {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        InnerClassSingleton s1;
        InnerClassSingleton s2 = InnerClassSingleton.getInstance();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("InnerClassSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();
            FileInputStream fis = new FileInputStream("InnerClassSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (InnerClassSingleton)ois.readObject();
            ois.close();
            System.out.println(s1);
            System.out.println(s2);
            // 结果false
            System.out.println(s1 == s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
