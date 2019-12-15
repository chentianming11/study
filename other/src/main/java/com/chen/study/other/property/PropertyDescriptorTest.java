package com.chen.study.other.property;

import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 陈添明
 * @date 2019/12/10
 */
public class PropertyDescriptorTest {


    @Test
    public void test() throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        Person person = new Person();
        PropertyDescriptor descriptor = new PropertyDescriptor("name", Person.class);
        Method writeMethod = descriptor.getWriteMethod();
        writeMethod.invoke(person, "test");
        System.out.println(person);
    }
}
