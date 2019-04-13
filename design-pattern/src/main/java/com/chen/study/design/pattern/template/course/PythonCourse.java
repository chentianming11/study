package com.chen.study.design.pattern.template.course;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class PythonCourse extends NetworkCourse {

    /**
     * python没有课后作业
     */
    @Override
    protected void feedback() {
        System.out.println("python反馈收集");
    }
}
