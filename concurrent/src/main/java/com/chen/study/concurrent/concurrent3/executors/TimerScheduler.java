package com.chen.study.concurrent.concurrent3.executors;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class TimerScheduler {

    /**
     * scheduler 定时器
     * 1. Timer/TimerTask：不保证间隔执行
     * 2. SchedulerExecutorService
     * 3. crontab：保证间隔执行
     * 4. cron4j
     * 5. quartz：保证间隔执行
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("======：" + System.currentTimeMillis());
            }
        };
        // 延时1s执行
//        timer.schedule(timerTask, 1000);
        // 延时1s，每1s执行一次
        // 实际上是任务执行结束，过1s再执行下一次任务
        timer.schedule(timerTask, 1000, 1000);
    }
}
