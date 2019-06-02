package com.chen.study.java8.stream;

import com.chen.study.java8.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 查找与匹配
 * allMatch--检查是否匹配所有元素
 * anyMatch-- 检查是否至少匹配一个元素
 * noneMatch--检查是否没有匹配所有元素
 * findFirst--返回第一个元素
 * findAny--返回流中任意元素
 * count--返回流中元素个数
 * max--返回最大值
 * min--返回最小值
 * <p>
 * 归约
 * reduce(T identity, BinaryOperator)/ reduce(BinaryOperator)
 * --可以将流中的元素反复结合起来，得到一个值
 * <p>
 * 收集
 * collect--将流转换为其他形式。
 * Created by chen on 2018/2/17.
 */
public class TestStream3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 13, 999.99),
            new Employee("李四", 38, 111.111),
            new Employee("王五", 59, 222.22),
            new Employee("赵六", 16, 333.33),
            new Employee("赵六", 16, 333.33),
            new Employee("赵六", 16, 333.33),
            new Employee("田七", 9, 777.77)
    );

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = list.stream();
        Integer reduce = stream.reduce(1, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("-------------------|");
        Optional<Double> optional = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(optional.get());


    }

    @Test
    public void test2() {
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("--------------------");
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        // 平均值
        employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        // 总和
        employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        // 最大值
        employees.stream().max(Comparator.comparingDouble(Employee::getSalary));


    }
}
