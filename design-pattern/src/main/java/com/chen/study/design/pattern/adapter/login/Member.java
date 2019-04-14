package com.chen.study.design.pattern.adapter.login;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/4/14
 */
@Data
@Accessors(chain = true)
public class Member {

    private String username;
    private String password;
    private String mid;
    private String info;
}
