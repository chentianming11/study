package com.chen.study.guava.newcollection;

import com.google.common.collect.ArrayListMultimap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MultiMap可以很容易的将一个键映射到多个值
 * Created by chen on 2018/2/25.
 */
public class TestMultiMap {

    @Test
    public void testMultiMap() {
        ArrayListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a", "1"); // 添加 key=a value=1
        listMultimap.put("a", "5");
        // 添加 key=b value=1,2,3
        listMultimap.putAll("b", Arrays.asList("1", "2", "3"));
        System.out.println(listMultimap);
        // 判断是否包含key=b value=1的entry
        System.out.println(listMultimap.containsEntry("a", "1"));
        // 判断是否包含key=c
        System.out.println(listMultimap.containsKey("c"));
        // 获取所有的entry条目
        Collection<Map.Entry<String, String>> entries = listMultimap.entries();
        entries.forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));
        // 遍历Multimap
        listMultimap.forEach((key, value) -> System.out.println(key + "==" + value));

        // 根据key获取对应的值集合
        System.out.println(listMultimap.get("b"));
        // 获取所有的key到MultiSet （含重）
        System.out.println(listMultimap.keys());
        // 获取key到set中 （去重）
        System.out.println(listMultimap.keySet());
        // 移除key=a value=1 这个条目
        listMultimap.remove("a", "1");
        // 移除key=a 的所有条目
        // 清除键对应的所有值，返回的集合包含所有之前映射到 K 的值，但修改这个集合就不会影响 Multimap 了。
        List<String> list = listMultimap.removeAll("a");
        // 替换key=b的所有value值
        // 清除键对应的所有值，并重新把 key 关联到 Iterable 中的每个元素。返回的集合包含所有之前映射到 K 的值。
        List<String> list1 = listMultimap.replaceValues("b", Arrays.asList("4", "5"));
        System.out.println(listMultimap);
    }
}
