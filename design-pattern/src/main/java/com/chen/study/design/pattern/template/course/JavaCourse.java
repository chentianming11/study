package com.chen.study.design.pattern.template.course;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class JavaCourse extends NetworkCourse {

    private boolean needHomeworkFlag;

    public JavaCourse(boolean needHomeworkFlag) {
        this.needHomeworkFlag = needHomeworkFlag;
    }

    @Override
    protected boolean needHomework() {
        return needHomeworkFlag;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查Java作业");
    }

    @Override
    protected void feedback() {
        System.out.println("Java反馈收集");
    }
}
