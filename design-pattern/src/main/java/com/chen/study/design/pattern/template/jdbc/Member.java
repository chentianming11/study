package com.chen.study.design.pattern.template.jdbc;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
@Data
@Accessors(chain = true)
public class Member {

    private String username;
    private String password;
    private String nickName;
    private int age;
}
