package com.chen.study.java8.stream;

import com.chen.study.java8.Address;
import com.chen.study.java8.Employee;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author 陈添明
 * @date 2019/7/3
 */
public class TestFlatMap {

    public void test1() {
        Employee employee = Employee.builder().name("测试").age(10).salary(1.1)
                .address(new Address().setCity("上海").setProvince("上海").setCountry("中国"))
                .build();

        Employee employee2 = Employee.builder().name("测试2").age(10).salary(1.1)
                .address(new Address().setCity("上海2").setProvince("上海2").setCountry("中国2"))
                .build();

        List<Employee> list = Lists.newArrayList(employee, employee2);


    }
}
