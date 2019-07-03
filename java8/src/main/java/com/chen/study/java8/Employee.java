package com.chen.study.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chen on 2018/2/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Address address;
}
