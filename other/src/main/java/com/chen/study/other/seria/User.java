package com.chen.study.other.seria;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 陈添明
 * @date 2019/9/14
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Integer age;

    private String name;

}
