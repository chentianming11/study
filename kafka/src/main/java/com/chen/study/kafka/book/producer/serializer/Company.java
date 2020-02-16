package com.chen.study.kafka.book.producer.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String name;

    private String address;

}
