package com.chen.study.guava.cache;

import com.chen.study.guava.Employee;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by chen on 2018/2/26.
 */
public class TestCache {

    @Test
    public void testCache() throws ExecutionException, InterruptedException {
        // 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置并发级别8，并发级别是指同时可以写缓存的线程数
                .concurrencyLevel(8)
                // 设置缓存写入后8s过期
                .expireAfterWrite(8, SECONDS)
                // 设置缓存器的初始容量10
                .initialCapacity(10)
                // 设置最大容量100，超过就按LRU最近最少使用清理缓存
                .maximumSize(100)
                // 设置报告缓存命中状态
                .recordStats()
                // 设置缓存的移除通知
                .removalListener(removalNotification ->
                        System.out.println(removalNotification.getKey() + "被移除了"
                                + "原因是：" + removalNotification.getCause()))
                .build(CacheLoader.from(() -> new Employee())
                );

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("张三", 20, 9000));
        employees.add(new Employee("李四", 24, 10000));
        employees.add(new Employee("王五", 21, 8000));
        employees.add(new Employee("赵六", 28, 13000));
        employees.add(new Employee("田七", 32, 17000));
        ImmutableMap<String, Employee> map = Maps.uniqueIndex(employees, (employee -> employee.getName()));
        cache.putAll(map);


        for (int i = 0; i < 20; i++) {
            // 从缓存中获取数据，如果不存在，则调用Callable接口加载数据
            Employee employee = cache.get("张三");
            System.out.println(employee);
            TimeUnit.SECONDS.sleep(1);

        }

        System.out.println("打印缓存状态");
        System.out.println(cache.stats());

    }


}
