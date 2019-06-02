package com.chen.study.java8.lambda;

import com.study.demo.entity.Employee;
import com.study.demo.java8.fun.MyFun;
import com.study.demo.java8.fun.MyString;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 一. Lambda表达式的基础语法：Java8中引入一个新的操作符“->”
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式需要执行的功能；Lambda体
 * <p>
 * 语法格式一：无参数无返回值
 * () -> System.out.println("hello")
 * 语法方式二：有一个参数并无返回值
 * (x) -> System.out.println(x)
 * 语法方式三：若只有一个参数，小括号可以省略
 * 语法格式四：有两个以上的参数，并且Lambda体中有多条语句
 * (x, y) -> {...}
 * 语法格式五：如果Lambda体中只有一条语句，{}和return都可以省略
 * 语法格式六：Lambda表达式的数据类型可以省略不写，类型推断
 * <p>
 * 左右遇一括号省
 * 左侧推断类型省
 * <p>
 * 二。Lambda表达式需要函数式接口的支持
 * 函数式接口：接口中只有一个抽象方法，称为函数式接口。可以使用@FunctionalInterface修饰
 */
public class TestLambda2 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 13, 999.99),
            new Employee("李四", 38, 111.111),
            new Employee("王五", 59, 222.22),
            new Employee("赵六", 16, 333.33),
            new Employee("田七", 9, 777.77)
    );

    @Test
    public void test1() {
        Runnable r1 = () -> System.out.println("hello Lambda");
        r1.run();
    }

    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("我的存在");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return x.compareTo(y);
        };
    }

    // 需求：对一个数进行运算
    @Test
    public void test5() {
        System.out.println(operation(100, (x) -> x * x));
        System.out.println(operation(100, (x) -> x + x));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }

    /**
     * 调用Collections.sort()方法。通过定制排序比较两个employee
     * 先按年龄比，年龄相同按姓名比。使用Lambda作为参数传递。
     */
    @Test
    public void test6() {

        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getAge() - e2.getAge();
            }
        });

    }

    /**
     * 声明一个函数式接口；接口中有抽象方法public String getValue(String str)
     * 声明类 TestLambda，使用接口作为参数。将字符串的首字母转为大写并返回
     * 再将字符串的第一个和第4个位置进行了截取子串。。。
     */

    @Test
    public void test7() {

        System.out.println(strHandle("hhhhhhh", (s) -> s.substring(2)));

    }

    public String strHandle(String str, MyString ms) {
        return ms.getValue(str);
    }

}
