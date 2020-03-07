package com.chen.study.quartz;

import com.chen.study.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 陈添明
 * @date 2020/3/7
 */
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException {

        /**
         * 创建Job
         * 必须要指定 JobName 和 groupName，两个合起来是唯一标识符。
         可以携带 KV 的数据(JobDataMap)，用于扩展属性，在运行的时候可以从 context获取到。
         */
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("gupao", "2673")
                .usingJobData("moon", 5.21F)
                .build();

        /**
         * 创建Trigger: 基于 SimpleTrigger 定义了一个每 2 秒钟运行一次、不断 重复的 Trigger
         */
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        /**
         * 创建Scheduler: 通过 Factory 获取调度器的实例，把 JobDetail 和 Trigger 绑定，注册到容器中。
         */

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }
}
