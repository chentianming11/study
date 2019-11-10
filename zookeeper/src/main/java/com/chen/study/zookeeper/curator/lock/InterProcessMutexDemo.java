package com.chen.study.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * 然后创建一个InterProcessMutexDemo类， 它负责请求锁， 使用资源，释放锁这样一个完整的访问过程。
 * @author 陈添明
 * @date 2018/12/15
 */

public class InterProcessMutexDemo {


    /**
     * 代码也很简单，生成10个client， 每个client重复执行10次 请求锁–访问资源–释放锁的过程。
     * 每个client都在独立的线程中。 结果可以看到，锁是随机的被每个实例排他性的使用。
     */

    private InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public InterProcessMutexDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        this.lock = new InterProcessMutex(client, lockPath);
    }


    /**
     * 同一线程再次 acquire，首先判断当前的 映射表内(threadData)是否有该线程的锁信息，如果有 则原子+1，然后返回
     * 可重入互斥锁
     * 加锁执行
     * @param time
     * @param unit
     * @throws Exception
     */
    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + "获取互斥锁锁失败");
        }
        try {
            System.out.println(clientName + " 获取到互斥锁");
            resource.use(); //access resource exclusively
        } finally {
            System.out.println(clientName + " 释放互斥锁");
            lock.release(); // always release the lock in a finally block
        }
    }

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        final TestingServer server = new TestingServer();
        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = () -> {
                    // 创建客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                    try {
                        // 启动
                        client.start();
                        final InterProcessMutexDemo example = new InterProcessMutexDemo(client, PATH, resource, "Client " + index);
                        // 执行
                        for (int j = 0; j < REPETITIONS; ++j) {
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        CloseableUtils.closeQuietly(client);
                    }
                    return null;
                };
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
            CloseableUtils.closeQuietly(server);
        }
    }

}
