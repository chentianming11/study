package com.chen.study.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据操作
 *
 * @author 陈添明
 * @date 2018/12/13
 */
public class CuratorOperateTest {

    private final static String connectionInfo = "127.0.0.1:2181";
    CuratorFramework client;

    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(connectionInfo)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();
    }

    /**
     * 创建一个节点，初始内容为空
     * 如果没有设置节点属性，节点创建模式默认为持久化节点，内容默认为空
     */
    @Test
    public void create1() throws Exception {
        String s = client.create().forPath("/path");
        System.out.println(s);
    }


    /**
     * 创建一个节点，附带初始化内容
     */
    @Test
    public void create2() throws Exception {
        String s = client.create().forPath("/path2", "test1".getBytes());
    }


    /**
     * 创建一个节点，指定创建模式（临时节点），内容为空
     */
    @Test
    public void create3() throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/path3");
    }

    /**
     * 创建一个节点，指定创建模式（临时节点），附带初始化内容
     */
    @Test
    public void create4() throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/path", "demo".getBytes());
    }


    /**
     * 创建一个节点，指定创建模式（临时节点），附带初始化内容，并且自动递归创建父节点
     * 这个creatingParentContainersIfNeeded()接口非常有用，
     * 因为一般情况开发人员在创建一个子节点必须判断它的父节点是否存在，如果不存在直接创建会抛出NoNodeException，
     * 使用creatingParentContainersIfNeeded()之后Curator能够自动递归创建所有所需的父节点。
     */
    @Test
    public void create5() throws Exception {
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/path/b/c", "init".getBytes());
    }


    /**
     * 删除一个节点
     * 注意，此方法只能删除叶子节点，否则会抛出异常。
     */
    @Test
    public void delete1() throws Exception {
        client.delete().forPath("/path");
    }

    /**
     * 删除一个节点，并且递归删除其所有的子节点
     */
    @Test
    public void delete2() throws Exception {
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/path");
    }


    /**
     * 删除一个节点，强制指定版本进行删除
     */
    @Test
    public void delete3() throws Exception {
        client.delete()
                .withVersion(100)
                .forPath("/path");
    }

    /**
     * 删除一个节点，强制保证删除
     * guaranteed()接口是一个保障措施，只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到删除节点成功。
     */
    @Test
    public void delete4() throws Exception {
        client.delete()
                .guaranteed()
                .forPath("/path");
    }


    /**
     * 上面的多个流式接口是可以自由组合的
     */
    @Test
    public void delete5() throws Exception {
        client.delete()
                .guaranteed()
                .deletingChildrenIfNeeded()
                .withVersion(10086)
                .forPath("path");
    }


    /**
     * 读取一个节点的数据内容
     */
    @Test
    public void read1() throws Exception {
        byte[] data = client.getData().forPath("/path2");
    }

    /**
     * 读取一个节点的数据内容，同时获取到该节点的stat
     */
    @Test
    public void read2() throws Exception {
        Stat stat = new Stat();
        byte[] data = client.getData()
                .storingStatIn(stat)
                .forPath("/path2");
        String s = new String(data);
        System.out.println(stat);
    }

    /**
     * 更新一个节点的数据内容
     */
    @Test
    public void update1() throws Exception {
        client.setData().forPath("/path","data".getBytes());
    }


    /**
     * 更新一个节点的数据内容，强制指定版本进行更新
     */
    @Test
    public void update2() throws Exception {
        client.setData().withVersion(10086).forPath("/path","data".getBytes());

    }

    /**
     * 检查节点是否存在
     * 不存在，返回null
     */
    @Test
    public void check1() throws Exception {
        Stat stat = client.checkExists().forPath("/path");

        System.out.println("===================");
        System.out.println(stat);
    }

    /**
     * 获取某个节点的所有子节点路径
     */
    @Test
    public void children() throws Exception {
        List<String> list = client.getChildren().forPath("/path");

        System.out.println("===================");
        System.out.println(list);
    }

    /**
     * 事务
     */
    @Test
    public void transaction() throws Exception {

        client.inTransaction()
                .check().forPath("/path")
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/path", "data".getBytes())
                .and()
                .setData().withVersion(1000).forPath("/path", "data2".getBytes())
                .and()
                .commit();

    }

    /**
     * 异步接口
     */
    @Test
    public void async() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                // 异步
//                .inBackground()
                .inBackground((client1, event) -> {
                    // 事件类型
                    CuratorEventType type = event.getType();
                    // 结果编码
                    int resultCode = event.getResultCode();
                    System.out.println("事件类型: " + type + ", 结果编码: " + resultCode);
                }, executor)
                .forPath("/path55");

        Thread.currentThread().join();
    }



}
