package com.chen.study.design.pattern.delegate.simple;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class EmployeeA implements IEmployee {


    @Override
    public void doing(String command) {
        System.out.println("员工A, 执行" + command);
    }
}
