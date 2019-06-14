package com.chen.study.custom.util.core;


import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.Objects;


/**
 * 断言工具类
 *
 * @author chenTianMing
 */
@UtilityClass
public class AppAssert {

    public static void notNull(Object o, String errorMessage) {
        if (o == null) {
            throw new AppException(errorMessage);
        }
    }

    public static void isNull(Object o, String errorMessage) {
        if (o != null) {
            throw new AppException(errorMessage);
        }
    }

    public static void notEmpty(Object o, String errorMessage) {
        if (Is.empty(o)) {
            throw new AppException(errorMessage);
        }
    }

    public static void isEmpty(Object o, String errorMessage) {
        if (!Is.empty(o)) {
            throw new AppException(errorMessage);
        }
    }

    public static void equals(Object o1, Object o2, String errorMessage) {
        if (!Objects.equals(o1, o2)) {
            throw new AppException(errorMessage);
        }
    }

    public static void notEquals(Object o1, Object o2, String errorMessage) {
        if (Objects.equals(o1, o2)) {
            throw new AppException(errorMessage);
        }
    }

    public static void dateLessThan(Date o1, Date o2, String errorMessage) {
        if (o1 == null || o2 == null) {
            return;
        }
        int i = o1.compareTo(o2);
        if (i > 0) {
            throw new AppException(errorMessage);
        }
    }


    public static void stringLengthMoreThan(String remark, int i, String errorMessage) {
        if (Objects.nonNull(remark) && remark.length() <= i) {
            throw new AppException(errorMessage);
        }
    }

    public static void stringLengthLessThan(String remark, int i, String errorMessage) {
        if (Objects.nonNull(remark) && remark.length() >= i) {
            throw new AppException(errorMessage);
        }
    }

    public static void isTrue(boolean b, String errorMessage) {
        if (b == false) {
            throw new AppException(errorMessage);
        }
    }

    public static void isFalse(boolean b, String errorMessage) {
        if (b == true) {
            throw new AppException(errorMessage);
        }
    }


}
