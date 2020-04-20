package com.example;

import static org.junit.Assert.assertTrue;

import com.example.distributedlock.MicroService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistributedLockApplicationTest {

    @Autowired
    private MicroService microService;

    public CuratorFramework init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace("base") // 包含隔离名称
                .build();
        client.start();
        return client;
    }

    @Test
    public void create() {
        CuratorFramework client = init();
        try {
            client.create().creatingParentContainersIfNeeded() // 递归创建所需父节点
                    .withMode(CreateMode.PERSISTENT) // 创建类型为持久节点
                    .forPath("/nodeA", "init".getBytes()); // 目录及内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        CuratorFramework client = init();
        try {
            byte[] bytes = client.getData().forPath("/nodeDem");
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        CuratorFramework client = init();
        try {
            client.setData()
                    .withVersion(10086) // 指定版本修改
                    .forPath("/nodeDem", "data".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        CuratorFramework client = init();
        try {
            client.delete()
                    .guaranteed()  // 强制保证删除
                    .deletingChildrenIfNeeded() // 递归删除子节点
//                    .withVersion(10086) // 指定删除的版本号
                    .forPath("/nodeDem");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void transaction() {
        CuratorFramework client = init();
        try {
            client.inTransaction().check().forPath("/nodeA")
                    .and()
                    .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeB", "init".getBytes())
                    .and()
                    .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeC", "init".getBytes())
                    .and()
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkExists() {
        CuratorFramework client = init();
        try {
            Stat stat = client.checkExists() // 检查是否存在
                    .forPath("/nodeA");
            System.out.println("============"+stat.toString());
            List<String> strings = client.getChildren().forPath("/nodeA");// 获取子节点的路径
            System.out.println("============"+strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock() {
    }
}
