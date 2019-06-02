package com.chen.study.zookeeper.curator.lock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 共享资源
 */
public class FakeLimitedResource {
    private final AtomicBoolean inUse = new AtomicBoolean(false);
    private final Random random = new Random(System.currentTimeMillis());

    /**
     * use方法最多只能有一个客户端调用
     * 否则，抛出异常
     * @throws InterruptedException
     */
    public void use() throws InterruptedException {
        // 真实环境中我们会在这里访问/维护一个共享的资源
        //这个例子在使用锁的情况下不会非法并发异常IllegalStateException
        //但是在无锁的情况由于sleep了一段时间，很容易抛出异常
        if (!inUse.compareAndSet(false, true)) {
            throw new IllegalStateException("同一时间，只能被一个客户端访问！！！");
        }
        try {
            Thread.sleep(random.nextInt(2_000));
        } finally {
            inUse.set(false);
        }
    }
}
