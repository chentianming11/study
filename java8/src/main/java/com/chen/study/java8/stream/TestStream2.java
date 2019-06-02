package com.chen.study.java8.stream;

import com.chen.study.java8.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建Stream的三个步骤：
 * 1. 创建Stream
 * 2. 中间操作
 * 3. 终止操作
 * Created by chen on 2018/2/17.
 */
public class TestStream2 {

    /**
     * 筛选与切片
     * filter--接收Lambda，从流中排除某些元素
     * limit--截断流，是元素不超过指定数量
     * skip（n）--跳过元素，返回一个扔掉了前n个元素的流
     * distinct--去重
     * <p>
     * 映射
     * map--接收Lambda，将元素转换成其他形式或者提取信息。
     * 接收一个函数作为参数，该函数会作用到每一个元素上；并将其映射成一个新的元素
     * flatMap--接收一个函数作为参数，将流中的每一个元素加到流中
     * <p>
     * 排序
     * store()--自然排序
     * store(Comparator com)--定制排序
     */
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 13, 999.99),
            new Employee("李四", 38, 111.111),
            new Employee("王五", 59, 222.22),
            new Employee("赵六", 16, 333.33),
            new Employee("赵六", 16, 333.33),
            new Employee("赵六", 16, 333.33),
            new Employee("田七", 9, 777.77)
    );

    public static Stream<Character> filterStream(String string) {
        List<Character> list = new ArrayList<>();
        char[] array = string.toCharArray();
        for (char c : array) {
            list.add(c);
        }

        return list.stream();
    }

    @Test
    public void test1() {
        List<Employee> collect = employees.stream()
                .filter((e) -> e.getAge() > 35)
                .sorted(Comparator.comparing(Employee::getAge))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void test2() {
        employees.stream()

                .forEach(employee -> {
                    if (employee.getAge() < 30) {
                        employee.setAge(0);
                    }
                });
        System.out.println(employees);
    }

    @Test
    public void test3() {
        employees.stream()
                .filter((e) -> e.getSalary() > 100)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream().map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-----------------------------");
        employees.stream()
                .map(employee -> employee.getName())
                .forEach(System.out::println);
    }

    @Test
    public void test5() {


    }


}
