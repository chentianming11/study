package com.chen.study.design.pattern.singleton.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射破坏单例测试
 * @author 陈添明
 * @date 2019/3/27
 */
public class ReflectDestroySingletonTest {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<InnerClassSingleton> clzz = InnerClassSingleton.class;
        Constructor<InnerClassSingleton> constructor = clzz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        InnerClassSingleton o1 = constructor.newInstance();
        InnerClassSingleton o2 = InnerClassSingleton.getInstance();
        // 结果：false
        System.out.println(o1 == o2);
    }
}
