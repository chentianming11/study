package com.chen.study.concurrent.concurrent3.executors;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 保证间隔执行
 * @author 陈添明
 * @date 2018/11/13
 */
public class QuartzScheduler {

    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
