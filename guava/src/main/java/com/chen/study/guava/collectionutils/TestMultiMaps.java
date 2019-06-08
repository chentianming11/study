package com.chen.study.guava.collectionutils;

import com.alibaba.fastjson.JSON;
import com.chen.study.guava.Employee;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Created by chen on 2018/2/26.
 */
public class TestMultiMaps {

    @Test
    public void test1() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("张三", 20, 9000));
        employees.add(new Employee("张三", 20, 9000));
        employees.add(new Employee("ceshi", 20, 9000));
        employees.add(new Employee("李四", 24, 10000));
        employees.add(new Employee("王五", 21, 8000));
        employees.add(new Employee("王五", 21, 8000));
        employees.add(new Employee("张三", 28, 13000));
        employees.add(new Employee("田七", 32, 17000));
        employees.add(new Employee("张三", 32, 17000));
        // 有一组对象，它们有共同的特定属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
        ImmutableListMultimap<String, Employee> index = Multimaps.index(employees, employee -> employee.getName());


        String toJSONString = JSON.toJSONString(index);
        System.out.println(toJSONString);

        index.forEach((k, v) -> System.out.println(k + "=" + v));
        ImmutableList<Employee> 张三 = index.get("张三");


        System.out.println("-------------------------");
        ImmutableSet<String> keySet = index.keySet();
        keySet.forEach(key -> {
            ImmutableList<Employee> list = index.get(key);
            System.out.println("key=" + key + " value=" + list);
        });
        System.out.println("-------------------------");

        // 反转map
        HashMultimap<String, Integer> multimap = HashMultimap.create();
        multimap.putAll("a", Arrays.asList(1, 2, 3));
        multimap.putAll("b", Arrays.asList(3, 6, 9));
        multimap.putAll("c", Arrays.asList(1, 5, 7));
        TreeMultimap<Integer, String> invertFrom = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(invertFrom);

        // 将普通Map转变成MultiMap,然后再进行反转  （可将普通map进行反转了）
        ImmutableMap<String, Integer> immutableMap = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        Map<String, Integer> map = Maps.newHashMap();
        map.putIfAbsent("a", 1);
        map.putIfAbsent("b", 1);
        map.putIfAbsent("c", 2);
        SetMultimap<String, Integer> setMultimap = Multimaps.forMap(map);
        System.out.println("将map转成Multimap" + setMultimap);
        HashMultimap<Integer, String> invertFrom1 = Multimaps.invertFrom(setMultimap, HashMultimap.create());
        System.out.println("再将Multimap反转" + invertFrom1);


    }

    @Test
    public void test2() {
        ArrayList<Employee> employees = new ArrayList<>();
        ImmutableMap<String, Employee> map = Maps.uniqueIndex(employees, Employee::getName);

        Employee ta = map.get("ta");
        System.out.println(ta);
    }

    @Test
    public void test3() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(null);
        employees.add(null);
        employees.add(null);
        System.out.println(employees);

        employees.removeAll(Collections.singleton(null));
        System.out.println(employees);
    }
}
