package com.example;

import com.example.distributedlock.MicroService;
import com.example.utils.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.*;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DistributedLockApplicationTest {

    @Autowired
    private MicroService microService;
    @Autowired
    CuratorFramework cruatorFramework;

    @Autowired
    RedisDistributedLock redisDistributedLock;


    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;

    private static final String PATH = "/examples/locks";

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
            System.out.println("============" + stat.toString());
            List<String> strings = client.getChildren().forPath("/nodeA");// 获取子节点的路径
            System.out.println("============" + strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**分布式锁测试*/
    @Test
    public void lock() {
        final CountDownLatch latch = new CountDownLatch(QTY);
        ExecutorService executor = Executors.newFixedThreadPool(QTY);
        //        ExecutorService service = Executors.newFixedThreadPool(QTY);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < QTY; i++) {

            service.submit(new Thread(() -> {
                InterProcessMutex mutex = new InterProcessMutex(cruatorFramework, PATH);
                for (int j = 0; j < REPETITIONS; j++) {
                    microService.getLockByCurator(mutex);
                }
                latch.countDown();
            }));
        }

        try {
            latch.await();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }


    /**分布式锁测试*/
    @Test
    public void test() {
//        ExecutorService service = Executors.newFixedThreadPool(QTY);
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {

                            ExampleClientThatLocks example = new ExampleClientThatLocks(cruatorFramework, PATH, new FakeLimitedResource(), "Client " + index);
                            for (int j = 0; j < REPETITIONS; ++j) {
                                example.doWork(10, TimeUnit.SECONDS);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } catch (Exception e) {
                            e.printStackTrace();
                            // log or do something
                        }
                        return null;
                    }
                };
                service.submit(task);
            }

            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(cruatorFramework);
        }
    }

    /**
     * @describe: 测试获取分布式锁
     * @author: WangKun
     * @date: 2019-02-26 02:56
     **/
    @Test
    public void contextLoads() {
        Object lock = redisDistributedLock.lock("key1", "val3", 100);
        System.out.println(lock);
    }


}
