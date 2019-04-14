package com.chen.study.design.pattern.adapter.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/4/14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ResultMsg {

    private int code;
    private String msg;
    private Object data;
}
