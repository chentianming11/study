package com.chen.study.zookeeper.curator.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @author 陈添明
 * @date 2018/12/14
 */
public class PathCacheTest {

    private final static String connectionInfo = "127.0.0.1:2181";

    private static final String PATH = "/example/pathCache";

    public static void main(String[] args) throws Exception {
        // 创建curator客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectionInfo)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();

        // 创建path cache
        /**
         * 注意：如果new PathChildrenCache(client, PATH, true)中的参数cacheData值设置为false，
         * 则示例中的event.getData().getData()、data.getData()将返回null，cache将不会缓存节点数据。
         */
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();

        // 添加一个监听器
        cache.getListenable()
                .addListener((client1, event) -> {
                    // 事件类型
                    PathChildrenCacheEvent.Type type = event.getType();
                    System.out.println("事件类型：" + type);
                    // 子节点数据
                    ChildData data = event.getData();
                    System.out.println("子节点数据：" + data);
                });



        while (true){
            System.out.println("=======遍历子节点数据===========");
            List<ChildData> currentData = cache.getCurrentData();
            currentData.forEach(childData -> {
                System.out.println("遍历子节点数据：" + childData);
            });
            Thread.sleep(1000);
        }

//        client.close();
//        cache.close();



    }
}
