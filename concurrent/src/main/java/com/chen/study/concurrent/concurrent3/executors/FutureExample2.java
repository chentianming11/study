package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.*;

/**
 * @author 陈添明
 * @date 2018/11/18
 */
public class FutureExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        isDone();

        cancel();
    }



    private static void isDone() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 10;
        });

        System.out.println("=========");
        /**
         * isDone：是否完成，异步
         * 返回true：
         *      Completion may be due to normal termination, an exception, or
         *       cancellation -- in all of these cases, this method will return
         *  1.正常结束
         *  2. 出现异常
         *  3. 被取消
         */
        Thread.sleep(5_000);
        System.out.println(future.isDone());
        System.out.println(future.get());
    }


    /**
     * 1. 取消失败的原因：
     *  1. 任务已经结束
     *  2. 任务已经被取消
     *  3. 其他原因
     *
     * 2. cancel之后，实际上任务还会继续执行。
     *
     *
     */
    private static void cancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成： " + 10);

            return 10;
        });
        boolean cancel = future.cancel(true);
        System.out.println(cancel);
        System.out.println(future.isDone());
    }
}
