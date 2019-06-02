package com.chen.study.zookeeper.curator.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedBarrierDemo {

    private static final int QTY = 5;
    private static final String PATH = "/examples/barrier";


    /**
     * 这个例子创建了controlBarrier来设置栅栏和移除栅栏。 我们创建了5个线程，在此Barrier上等待。
     * 最后移除栅栏后所有的线程才继续执行。
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        try (TestingServer server = new TestingServer()) {
            CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
            client.start();
            ExecutorService service = Executors.newFixedThreadPool(QTY);

            // 创建barrier
            DistributedBarrier controlBarrier = new DistributedBarrier(client, PATH);
            // 设置barrier
            controlBarrier.setBarrier();

            for (int i = 0; i < QTY; ++i) {
                final DistributedBarrier barrier = new DistributedBarrier(client, PATH);
                final int index = i;
                Callable<Void> task = () -> {
                    Thread.sleep((long) (3 * Math.random()));
                    System.out.println("Client #" + index + " waits on Barrier");
                    barrier.waitOnBarrier();
                    System.out.println("Client #" + index + " begins");
                    return null;
                };
                service.submit(task);
            }
            Thread.sleep(10000);
            System.out.println("all Barrier instances should wait the condition");
            // 放行
            controlBarrier.removeBarrier();
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);

            Thread.sleep(20000);
        }
    }
}