package com.chen.study.java8.functionref;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 方法引用：如果Lambda体中的内容有方法已经实现了，我们可以使用方法引用
 * （可以理解为方法引用是Lambda表达式的另外一种表达形式）
 * <p>
 * 主要有三种语法格式：
 * Lambda体中的调用方法的参数列表和返回值要和函数式接口中抽象方法的参数和返回值一致；
 * <p>
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名：若Lambda表达式的调用的第一个参数是方法的调用者
 * 第二个参数是方法的参数时，才可以使用这种形式
 * <p>
 * 构造器引用
 * 类::new
 * <p>
 * 数组引用：Type[]::new
 * Created by chen on 2018/2/13.
 */
public class TestMehodRef {

    // 对象名::实例方法名
    @Test
    public void test1() {
        // 传统的Lambda表达式的形式
        Consumer<String> consumer = (x) -> System.out.println(x);
        // 可以简化为使用方法引用的形式
        Consumer<String> consumer1 = System.out::println;

    }

    // 类::静态方法名
    @Test
    public void test2() {
        Comparator<Integer> comparator = Integer::compare;
    }

    // 类::实例方法名
    @Test
    public void test3() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;

    }

    // Type::new
    @Test
    public void test4() {
        Function<Integer, String[]> function = String[]::new;
    }


}
