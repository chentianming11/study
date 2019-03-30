package com.chen.study.design.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器缓存单例
 *
 * @author 陈添明
 * @date 2019/3/30
 */
public class ContainerSingleton {

    private ContainerSingleton() {}

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static <T> T getBean(Class<T> tClass) {
        Object o = ioc.get(tClass.getName());
        if (o != null) {
            return (T) o;
        }
        synchronized (ioc) {
            T t = null;
            try {
                t = tClass.newInstance();
                ioc.put(tClass.getName(), t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }
    }
}
