package com.chen.spring.action.c3.highbean.ambiguity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Component
public class TestQualifier {

    @Autowired
    @Qualifier("iceCream")
    private Dessert dessert;

}
