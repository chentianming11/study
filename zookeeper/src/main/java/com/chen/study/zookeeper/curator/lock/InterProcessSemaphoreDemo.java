package com.chen.study.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class InterProcessSemaphoreDemo {


    private static final int MAX_LEASE = 10;
    private static final String PATH = "/examples/locks";

    /**
     * 首先我们先获得了5个租约， 最后我们把它还给了semaphore。 接着请求了一个租约，
     * 因为semaphore还有5个租约，所以请求可以满足，返回一个租约，还剩4个租约。
     * 然后再请求5个租约，因为租约不够，阻塞到超时，还是没能满足，返回结果为null(租约不足会阻塞到超时，然后返回null，不会主动抛出异常；如果不设置超时时间，会一致阻塞)。
     *
     上面说讲的锁都是公平锁(fair)。 总ZooKeeper的角度看， 每个客户端都按照请求的顺序获得锁，不存在非公平的抢占的情况。
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
        try (TestingServer server = new TestingServer()) {

            // 创建客户端并启动
            CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
            client.start();

            // 信号量
            InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, PATH, MAX_LEASE);

            // 获取5个租约
            Collection<Lease> leases = semaphore.acquire(5);
            System.out.println("get " + leases.size() + " leases");

            // 获取1个租约
            Lease lease = semaphore.acquire();
            System.out.println("get another lease");

            // 处理资源
            resource.use();

            // 在获取5个租约，由于最多只有10个，前面已经使用了6个，租约不足。
            // 如果在获得所有租赁之前时间到期，则获得的租赁子集将自动关闭
            Collection<Lease> leases2 = semaphore.acquire(5, 10, TimeUnit.SECONDS);
            System.out.println("Should timeout and acquire return " + leases2);

            // 返还一个租约
            System.out.println("return one lease");
            semaphore.returnLease(lease);

            // 返还多个租约
            System.out.println("return another 5 leases");
            semaphore.returnAll(leases);
        }
    }
}
