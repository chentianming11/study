package com.chen.study.design.pattern.template.course;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public abstract class NetworkCourse {

    /**
     * 创建课程 -- 定义算法骨架
     */
    public final void createCourse() {

        //1、发布预习资料
        this.postPreResource();

        //2、制作 PPT 课件
        this.createPPT();

        //3、在线直播
        this.liveVideo();

        //4、提交课件、课堂笔记
        this.postNote();

        //5、提交源码
        this.postSource();

        //6、布置作业，有些课是没有作业，有些课是有作业的
        // 如果有作业的话，检查作业，如果没作业，完成了
        if (needHomework()) {
            checkHomework();
        }

        // 7. 收集课后反馈
        this.feedback();
    }



    final void postPreResource() {
        System.out.println("发布预习资料");
    }

    final void createPPT() {
        System.out.println("制作 PPT 课件");
    }

    final void liveVideo() {
        System.out.println("在线直播");
    }

    final void postNote() {
        System.out.println("提交课件、课堂笔记");
    }

    final void postSource() {
        System.out.println("提交源码");
    }

    /**
     * 钩子方法:实现流程的微调
     * 是否有作业
     * @return
     */
    protected boolean needHomework() {
        return true;
    }

    /**
     * 默认空实现
     */
    protected void checkHomework() {}

    protected abstract void feedback();

}
