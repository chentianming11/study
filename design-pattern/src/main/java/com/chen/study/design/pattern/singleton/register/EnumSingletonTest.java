package com.chen.study.design.pattern.singleton.register;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举单例测试
 * @author 陈添明
 * @date 2019/3/30
 */
public class EnumSingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /**
         * 测试反射攻击
         */
        Class<EnumSingleton> clzz = EnumSingleton.class;
        Constructor<EnumSingleton> constructor = clzz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        EnumSingleton o1 = constructor.newInstance("INSTANCE", 1);
        EnumSingleton o2 = EnumSingleton.INSTANCE;
        // 结果：false
        System.out.println(o1 == o2);
    }
}
