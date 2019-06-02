package com.chen.study.guava.collectionutils;

import com.chen.study.guava.Employee;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chen on 2018/2/25.
 */
public class TestMaps {

    @Test
    public void test() {
        // 创建map
        Map<String, String> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet("a", "b", "c");
        // 为set集合中的每个key生成一个对应的value，然后转换成Map
        Map<String, String> asMap = Maps.asMap(set, s -> s + 1);
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "4");

        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("a", "1");
        map1.putIfAbsent("b", "10");
        map1.put("f", "3");
        map1.put("g", "8");
        // 比较2个Map，获得MapDifference对象
        MapDifference<String, String> mapDifference = Maps.difference(map, map1);
        // 2个Map都存在的映射项，包括匹配的键和值
        Map<String, String> common = mapDifference.entriesInCommon();
        System.out.println("2个Map都存在的映射项" + common);
        // 键相同的但不是值不同的映射项；
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = mapDifference.entriesDiffering();
        System.out.println("键相同的但不是值不同的映射项" + stringValueDifferenceMap);
        // 键只存在于左边的映射项
        System.out.println("键只存在于左边的映射项" + mapDifference.entriesOnlyOnLeft());

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("张三", 20, 9000));
        employees.add(new Employee("李四", 24, 10000));
        employees.add(new Employee("王五", 21, 8000));
        employees.add(new Employee("赵六", 28, 13000));
        employees.add(new Employee("田七", 32, 17000));


        // 为一组对象建立唯一索引；
        // 适用的场景：有一组对象，它们在某个属性上唯一；而我们需要按照这个属性查找对应的对象
        ImmutableMap<String, Employee> immutableMap = Maps.uniqueIndex(employees, employee -> employee.getName());
        System.out.println("为一组对象建立唯一索引；" + immutableMap);

        // 处理BiMap的工具方法
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.putIfAbsent("a", "1");
        biMap.putIfAbsent("b", "2");
        biMap.putIfAbsent("c", "3");
        BiMap<String, String> stringStringBiMap = Maps.synchronizedBiMap(biMap);
        System.out.println(stringStringBiMap);
        BiMap<String, String> unmodifiableBiMap = Maps.unmodifiableBiMap(biMap);
        System.out.println(unmodifiableBiMap);
    }
}
