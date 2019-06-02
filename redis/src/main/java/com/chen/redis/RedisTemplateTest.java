package com.chen.redis;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate测试类
 * Created by chenTianMing on 2018/6/13.
 */
public class RedisTemplateTest {


    private RedisTemplate redisTemplate;


    private StringRedisTemplate stringRedisTemplate;

    /**
     * redisTemplate.opsForValue(); 操作Object
     */
    @Test
    public void opsForValue() {
        // 设置key value
        redisTemplate.opsForValue().set("name", "tom");
        System.out.println(redisTemplate.opsForValue().get("name"));
        // 设置key value + 过期时间
        redisTemplate.opsForValue().set("name", "tom", 10, TimeUnit.SECONDS);
        // 加偏移量，表示从指定偏移量的位置开始复写value的值。
        redisTemplate.opsForValue().set("key", "redis", 6);
        // key不存在，则插入。  插入成功返回true，失败返回false
        redisTemplate.opsForValue().setIfAbsent("multi1", "multi1");

        // 以Map的形式 插入 多个键值对
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("multi1", "multi1");
        maps.put("multi2", "multi2");
        maps.put("multi3", "multi3");
        redisTemplate.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        // 为多个键分别取出它们的值
        System.out.println(redisTemplate.opsForValue().multiGet(keys));
        // 设置键的字符串值并返回其旧值
        redisTemplate.opsForValue().getAndSet("multi1", "test2");

        // 设置某个key的值自增，并返回自增后的值。（支持long和double）
        Long increment = redisTemplate.opsForValue().increment("increlong", 1);
    }

    /**
     * redisTemplate.opsForList() 操作list
     */
    @Test
    public void opsForList1() {
        // 向列表的的头部压入1个对象
        redisTemplate.opsForList().leftPush("list", "aaa");
        redisTemplate.opsForList().leftPush("list", "bbb");
        // 向列表的的头部压入多个对象
        redisTemplate.opsForList().leftPushAll("list", "ccc", "ddd");
        // 向列表的的头部压入1个集合
        redisTemplate.opsForList().leftPushAll("list", ImmutableList.of("eee", "fff"));

        /**
         * 把value值放到key对应列表中pivot值的左面，如果pivot值存在的话
         * 把 xxx 放在 bbb 的左边
         */
        redisTemplate.opsForList().leftPush("list", "bbb", "xxx");

        // 获取size
        System.out.println(redisTemplate.opsForList().size("list"));

        /**
         * 返回存储在键中的列表的指定元素。
         * 0：列表的开始位置
         * -1：列表的结束位置
         */
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));

        /**
         * 修剪现有列表，使其只包含指定的指定范围的元素。
         * 0：列表的开始位置
         * -1：列表的结束位置
         */
        redisTemplate.opsForList().trim("list", 0, 2);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));


        // 在列表中index的位置设置value值
        redisTemplate.opsForList().set("list", 1, "set");
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));

        // 根据下标获取元素的值
        redisTemplate.opsForList().index("list", 1);

        /**
         * 弹出最左边的元素，弹出之后该值在列表中将不复存在(不加时间单元，不会阻塞)
         * 如果加上时间单元的pop方法，列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
         */
        redisTemplate.opsForList().leftPop("list5", 10, TimeUnit.SECONDS);


        /**
         * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
         * 移除list列表最右边的元素，并添加到rightPopAndLeftPush列表的左边
         */
        redisTemplate.opsForList().rightPopAndLeftPush("list", "rightPopAndLeftPush");


    }

    @Test
    public void list2() {
        redisTemplate.opsForList().leftPushAll("list3", "aaa", "bbb", "ccc", "ddd", "ccc", "bbb", "xxx");
        System.out.println(redisTemplate.opsForList().range("list3", 0, -1));

        /**
         * 从存储在键中的列表中删除等于值的元素
         * count> 0：正向遍历，删除count个为value的元素
         * count <0：反向遍历，删除count个为value的元素
         * count = 0：删除等于value的所有元素。
         */
        redisTemplate.opsForList().remove("list3", 2, "bbb");
        System.out.println(redisTemplate.opsForList().range("list3", 0, -1));
    }

    /**
     * Redis的散列可以让用户将多个键值对存储到一个Redis键里面。
     */
    @Test
    public void hash() {
        // 向hash中put 单个键值对
        redisTemplate.opsForHash().put("redisHash", "name", "tom");
        redisTemplate.opsForHash().put("redisHash", "age", 26);
        redisTemplate.opsForHash().put("redisHash", "class", 6);
        // 向hash中putAll一个map （放置多个键值对）
        redisTemplate.opsForHash().putAll("redisHash1", ImmutableMap.of("name", "jax", "age", 15));

        // 删除给定的哈希hashKeys
        redisTemplate.opsForHash().delete("redisHash1", "name", "age");

        // 判断hashKey是否存在
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "ttt"));

        // 从键中的哈希获取给定hashKey的值
        System.out.println(redisTemplate.opsForHash().get("redisHash", "name"));

        // 从哈希中获取给定hashKey的值
        List<Object> kes = new ArrayList<Object>();
        kes.add("name");
        kes.add("age");
        System.out.println(redisTemplate.opsForHash().multiGet("redisHash", kes));

        // 通过给定的delta增加散列hashKey的值（整型） 也可以支持浮点型
        System.out.println(redisTemplate.opsForHash().increment("redisHash", "age", 1));

        // 获取key所对应的所有散列表的key
        System.out.println(redisTemplate.opsForHash().keys("redisHash"));

        // 获取key所对应的散列表的大小个数
        System.out.println(redisTemplate.opsForHash().size("redisHash"));

        // 根据key获取整个哈希存储的值
        System.out.println(redisTemplate.opsForHash().values("redisHash"));

        // 根据key获取整个哈希存储的键值
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
    }





}
