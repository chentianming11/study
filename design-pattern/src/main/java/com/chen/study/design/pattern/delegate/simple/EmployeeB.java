package com.chen.study.design.pattern.delegate.simple;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class EmployeeB implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("员工B, 执行" + command);
    }
}
