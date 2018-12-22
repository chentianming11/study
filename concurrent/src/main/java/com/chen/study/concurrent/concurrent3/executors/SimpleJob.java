package com.chen.study.concurrent.concurrent3.executors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("===========" + System.currentTimeMillis());
    }
}
