package com.chen.study.guava.newcollection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * 键值都要求唯一
 * Created by chen on 2018/2/25.
 */
public class TestBiMap {

    @Test
    public void testBiMap() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("a", "1"); // 添加一个键值
        // key相同，值覆盖
        biMap.put("a", "2");
        biMap.put("c", "3");
        biMap.put("d", "4");
        // 为已存在的value，映射新的key；就会报错
//        biMap.put("b", "2");
        // 使用forcePut方法强制为已存在的value覆盖新的key
        biMap.forcePut("b", "2");
        // 反转key-value
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println("反转：" + inverse);
        System.out.println(biMap);

        String s = JSON.toJSONString(biMap);
        System.out.println(s);
    }
}
