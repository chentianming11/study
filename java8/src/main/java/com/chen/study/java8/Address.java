package com.chen.study.java8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/7/3
 */
@Data
@Accessors(chain = true)
public class Address {

    private String country;

    private String province;

    private String city;

}
