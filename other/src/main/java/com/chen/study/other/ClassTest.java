package com.chen.study.other;

import org.junit.Test;

/**
 * @author 陈添明
 * @date 2019/9/6
 */
public class ClassTest {



    class Father {

    }

    class Son extends Father {

    }

    @Test
    public void testIsAssignableFrom() {
        System.out.println(Father.class.isAssignableFrom(Son.class)); // true
        System.out.println(Son.class.isAssignableFrom(Son.class)); // true
    }
}
