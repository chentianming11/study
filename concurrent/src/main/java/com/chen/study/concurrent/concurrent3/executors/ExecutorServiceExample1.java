package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 处理线程执行过程中的异常
 * @author 陈添明
 * @date 2018/11/13
 */
public class ExecutorServiceExample1 {

    public static void main(String[] args) throws InterruptedException {
//        isShutdown();

//        isTerminrated();
        exeRunnableError();



    }


    /**
     * 问题：
     * 1. 当调用shutdown()之后，可以再去执行runnable吗？
     *      不能，RejectedExecutionException
     */
    private static void isShutdown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 是否执行关闭
        System.out.println(executorService.isShutdown());
        // 关闭线程池，不阻塞
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> {
            System.out.println("hahahah");
        });

    }


    /**
     * 是否终结
     */
    private static void isTerminrated(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
        // 是否关闭  关闭是一个动作
        System.out.println("是否关闭：" + executorService.isShutdown());
        // 是否已经终结  终结是一个状态
        System.out.println("是否已经终结：" + executorService.isTerminated());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        System.out.println("是否正在终结：" + threadPoolExecutor.isTerminating());

    }

    /**
     * 执行过程出错，并进行处理
     * @throws InterruptedException
     */
    private static void exeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(
            new MyTask(i){
                @Override
                protected void error(Throwable cause) {
                    System.out.println("No:[" + i + "] 失败，更新status为error。");
                }

                @Override
                protected void done() {
                    System.out.println("No:[" + i + "] 成功，更新status为done。");
                }

                @Override
                protected void doExecute() {
                    // 会出现执行失败的线程
                    if (i % 3 == 0){
                        int temp = 1/0;
                    }
                }

                @Override
                protected void doInit() {
                    // do nothing
                }
        }));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("===================");

    }


    /**
     * 使用模板设计模式，封装任务处理成功或者是失败后的操作
     */
    private static abstract class MyTask implements Runnable {

        private final int no;

        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause){
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void done();

        protected abstract void doExecute();

        protected abstract void doInit();
    }



    /**
     * 通过给线程设置setUncaughtExceptionHandler
     * 来处理线程执行过程中的异常 -- 有问题，不推荐
     */
    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
           Thread thread = new Thread(r);
           thread.setName("my-thread-" + SEQ.getAndIncrement());
           // 为线程设置未捕获的异常处理器
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("线程：" + t.getName() + "  执行失败。");
                cause.printStackTrace();
                System.out.println("===============异常处理==============");
            });
            return thread;
        }
    }


}
