package com.chen.study.concurrent.concurrent3.juc.util.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class ForkJoinRecursiveAction {

    private final static int maxThreshold = 3;

    private final static AtomicInteger result = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        forkJoinPool.execute(new CalculateRecursiveAction(0, 10));
        forkJoinPool.submit((new CalculateRecursiveAction(0,10)));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println(result);
    }

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        public CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end -start <= maxThreshold){
                int sum = IntStream.rangeClosed(start, end).sum();
                result.getAndAdd(sum);
            }else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, middle);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);
                leftAction.fork();
                rightAction.fork();
            }
        }
    }
}
