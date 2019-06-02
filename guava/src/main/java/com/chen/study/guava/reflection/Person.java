package com.chen.study.guava.reflection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by chen on 2018/3/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private String name;
    private Integer age;
    private LocalDate birthday;


    // 公共方法
    public String getDescription() {
        return String.format("姓名：%s; 年龄：%d; 生日：%s", name, age, birthday);
    }

    // 私有方法
    private String getSecret() {
        return "这是一个秘密！！！";
    }
}
