package com.chen.study.java8.lambda;

import com.chen.study.java8.Employee;
import com.chen.study.java8.MyPredicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chen on 2018/2/12.
 */
public class TestLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 13, 999.99),
            new Employee("李四", 38, 111.111),
            new Employee("王五", 59, 222.22),
            new Employee("赵六", 16, 333.33),
            new Employee("田七", 9, 777.77)
    );

    /**
     * 传统的匿名内部类的形式
     */
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
    }

    /**
     * Lambda表达式
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    /**
     * 需求：查询年龄大于35岁的员工
     */

    // 方式1：封装方法
    @Test
    public void test3() {
        List<Employee> list = filterEmployees(this.employees);
        list.forEach(e -> System.out.println(e));
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        list.forEach(e -> {
            if (e.getAge() > 35) {
                emps.add(e);
            }
        });

        return emps;
    }

    public List<Employee> filter(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> emps = new ArrayList<>();
        list.forEach(e -> {
            if (myPredicate.predicate(e)) {
                emps.add(e);
            }
        });

        return emps;
    }

    // 方案二：匿名内部类
    @Test
    public void test4() {
        filter(employees, new MyPredicate<Employee>() {
            @Override
            public boolean predicate(Employee employee) {
                return employee.getAge() > 35;
            }
        }).forEach(e -> System.out.println(e));
    }

    //方式二：Lambda表达式

    /**
     * Lambda表达式可以作用于只有唯一抽象方法的接口
     * 其本质就是将表达式作为抽象方法的实现
     * 凡是通过匿名内部类方案且仅实现一个接口的方式，都可以使用Lambda表达式替代
     */
    public void test5() {

        /**
         * 这里(e) -> e.getAge() > 35就是Lambda表达式
         * 表达式的结果就是MyPredicate接口的实现
         * 表达式的内容e.getAge() > 35就是抽象方法的实现
         */
        filter(employees, (e) -> e.getAge() > 35).forEach(System.out::println);
    }

}
