package com.chen.study.concurrent.concurrent2.future;

/**
 * Future: 代表的是未来的一个凭据
 * FutureTask：将你的调用逻辑进行了隔离
 * FutureService：桥接
 * @author 陈添明
 * @date 2018/9/22
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 同步调用模式
         */
//        String result = get();
//        System.out.println(result);


        /**
         * Future模式
         * 如果结果没有准备好，调用future.get()就会阻塞
         */
       /* FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        });

        System.out.println("===========");
        System.out.println("做其他事情");
        Thread.sleep(10_000);
        System.out.println("++++++++++++++++++++++++++");

        System.out.println(future.get());*/


        /**
         * Future模式 -- 回调
         * 有结果自动执行回调方法。
         */
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        }, result -> System.out.println(result));

        System.out.println("===========");
        System.out.println("做其他事情");
        System.out.println("++++++++++++++++++++++++++");
    }

    // 假设一个操作需要耗时10s
    private static String get() throws InterruptedException {
        Thread.sleep(10_000);
        return "result";
    }
}
