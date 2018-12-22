package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by chen on 2018/11/13.
 */
public class ExecutorServiceExample3 {

    public static void main(String[] args) throws InterruptedException {
//        test();
//        allowsCoreThreadTimeOut();
//        testPreStartCoreThread();
        testAdvice();
    }

    private static void test() throws InterruptedException {
        ThreadPoolExecutor executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        /**
         * 此时线程数还是0，说明创建线程池的时候不会直接先创建5个核心线程
         */
        System.out.println(executor.getPoolSize());
        executor.execute(() -> System.out.println("=======") );
        Thread.sleep(1_000);
        /**
         * 提交一个任务后，线程数加1，
         * 但是创建后核心线程默认情况下不会销毁
         */
        System.out.println(executor.getPoolSize());

    }

    /**
     * 允许核心线程销毁
     * @throws InterruptedException
     */
    private static void allowsCoreThreadTimeOut() throws InterruptedException {
        ThreadPoolExecutor executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        /**
         * 设置线程空闲状态存活时间
         */
        executor.setKeepAliveTime(1, TimeUnit.SECONDS);

        /**
         * 允许核心线程销毁
         *  Core threads must have nonzero keep alive times
         *  核心线程空闲状态存活时间不能为0
         */
        executor.allowCoreThreadTimeOut(true);

        IntStream.range(0, 5).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("task-" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });


    }


    /**
     * 预先启动核心线程
     */
    private static void testPreStartCoreThread(){
        ThreadPoolExecutor executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        /**
         * prestartCoreThread()
         * 预先启动一个核心线程
         */
//        boolean b = executor.prestartCoreThread();

        /**
         * 预先启动所有核心线程
         */
        executor.prestartAllCoreThreads();

        System.out.println(executor.getPoolSize());

    }

    /**
     * 任务执行 切面advice
     * 可在任务执行之前或者之后加一些逻辑
     */
    private static void testAdvice(){
        ThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(1, 3,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
                r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(new MyRunnable(10) {
            @Override
            public void run() {
                System.out.println("任务执行中：" +  this.getData());
            }
        });
    }

    private abstract static class MyRunnable implements Runnable {
        private final int no;

        protected MyRunnable(int no) {
            this.no = no;
        }

        protected int getData(){
            return this.no;
        }

    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {


        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("初始化：" + ((MyRunnable)r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (t != null) {
                t.printStackTrace();
            }else {
                System.out.println("执行完成："+ ((MyRunnable)r).getData());
            }
        }
    }



}
