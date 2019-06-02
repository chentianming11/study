package com.chen.study.zookeeper.curator.leader;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

/**
 * @author 陈添明
 * @date 2018/12/14
 */
public class LeaderLatchTest {

    protected static String PATH = "/francis/leader";
    private final static String connectionInfo = "127.0.0.1:2181";
    private static final int CLIENT_QTY = 10;

    public static void main(String[] args) {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();

        try {

            /**
             * 创建10个客户端，并且创建对应的LeaderLatch
             */
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client
                        = CuratorFrameworkFactory.newClient(connectionInfo, new ExponentialBackoffRetry(20000, 3));
                clients.add(client);
                LeaderLatch latch = new LeaderLatch(client, PATH, "Client #" + i);

                latch.addListener(new LeaderLatchListener(){
                    @Override
                    public void isLeader() {
                        System.out.println("I am Leader");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println("I am not Leader");
                    }
                });

                examples.add(latch);
                client.start();
                latch.start();
            }

            Thread.sleep(10_000);
            /**
             * 获取当前的leader
             */
            LeaderLatch currentLeader = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            // 只能通过close释放当前的领导权
            currentLeader.close();

            Thread.sleep(5000);

            /**
             * 再次获取leader
             */
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (LeaderLatch latch : examples) {
                if (null != latch.getState())
                    CloseableUtils.closeQuietly(latch);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}
