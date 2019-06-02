package com.chen.study.guava.cache;

import com.chen.study.guava.Employee;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by chen on 2018/2/26.
 */
public class TestCache {

    @Test
    public void testCache() throws ExecutionException, InterruptedException {
        // 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        Cache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置并发级别8，并发级别是指同时可以写缓存的线程数
                .concurrencyLevel(8)
                // 设置缓存写入后8s过期
                .expireAfterWrite(8, SECONDS)
                // 设置缓存器的初始容量10
                .initialCapacity(10)
                // 设置最大容量100，超过就按LRU最近最少使用清理缓存 这种情况发生在缓存项的数目逼近限定值时。
                .maximumSize(100)
                // 设置报告缓存命中状态
                .recordStats()
                // 设置缓存的移除通知
                .removalListener(removalNotification ->
                        System.out.println(removalNotification.getKey() + "被移除了"
                                + "原因是：" + removalNotification.getCause()))
//                .build(CacheLoader.from(() -> new Employee("一开始就加载", 20, 9000)));
                .build();


        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("张三", 20, 9000));
        employees.add(new Employee("李四", 24, 10000));
        employees.add(new Employee("王五", 21, 8000));
        employees.add(new Employee("赵六", 28, 13000));
        employees.add(new Employee("田七", 32, 17000));
        ImmutableMap<String, Employee> map = Maps.uniqueIndex(employees, (employee -> employee.getName()));
        // 主动加载
        cache.putAll(map);

        // 从缓存中取值，没有取到返回null
        Employee 张三 = cache.getIfPresent("张三");
        System.out.println(张三);
        Employee 不存在 = cache.getIfPresent("不存在");
        System.out.println(不存在);

        // 如果有缓存则返回；否则运算、缓存、然后返回
        Employee employee1 = cache.get("不存在", () -> new Employee("不存在", 10, 4444));
        System.out.println(employee1);

        System.out.println("打印缓存状态");
        System.out.println(cache.stats());

        // 显示移除缓存
        cache.invalidate("不存在");
        // 移除所有
        cache.invalidateAll();

    }


}
