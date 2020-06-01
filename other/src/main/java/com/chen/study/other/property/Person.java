package com.chen.study.other.property;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/10/16
 */
@Data
@Accessors(chain = true)
public class Person {

    private Long id;

    private String name;

    private Integer age;

    private Long jobId;

    private String nikeName;
}
