package com.chen.study.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的4大核心函数式接口
 * Consumer<T>:消费型接口-----void accept(T t)
 * <p>
 * Supplier<T>:供给型接口：----- T get()
 * <p>
 * Function<T , R>:函数型接口-----R apply(T t)
 * <p>
 * Predicate<T>: 断言型接口-----boolean test(T t)
 * Created by chen on 2018/2/13.
 */
public class TestLambda3 {

    //Predicate<T> 断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "dd", "acbh");
        System.out.println(filerString(list, (e) -> e.length() > 3));
    }

    // 需求：将满足条件的字符串放入集合中去
    public List<String> filerString(List<String> list, Predicate<String> predicate) {
        List<String> strings = new ArrayList<>();
        list.forEach((e) -> {
            if (predicate.test(e)) {
                strings.add(e);
            }
        });
        return strings;
    }

    // Function<T, R>
    public void test3() {
        System.out.println(strhandler("  abn/cdf/ff/gh/jj   ", (str) -> str.trim()));
    }

    //需求：用于处理字符串
    public String strhandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Supplier<T>
    @Test
    public void test2() {
        getNumberList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }

    //需求：产生一些整数并放入集合
    public List<Integer> getNumberList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);

        }
        return list;
    }

    //Consumer<T>:消费性接口
    @Test
    public void test1() {
        happy(10000, (m) -> System.out.println("消费" + m));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
