package com.example.redisredlock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRedlockApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRedlockApplicationTests.class);

    @Autowired(required = false)
    RedisLocker distributedLocker;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() {
// 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket("k6");
        keyObj.set("v5236");
    }


    @Test
    public void contextLoads() throws InterruptedException {
//        for (int i = 0; i < 1; ++i) { // create and start threads
//            System.out.println("----------------"+i);
        new Thread(new Worker()).start();
//        }

//        for (int i = 0; i < 5; i++) {
//            ()->{
//                String threadName = Thread.currentThread().getName();
//            }
//        }
        Thread.sleep(5000);
//        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");
    }


    class Worker implements Runnable {
        public void run() {
            try {
                distributedLocker.lock("WK", new AquiredLockWorker<Object>() {

                    @Override
                    public Object invokeAfterLockAquire() {
                        return null;
                    }

                });
            } catch (Exception e) {

            }
        }

    }


    @Test
    public void test1() {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("RAtomicLong");

        Callable<Long> callable = () -> {
            String threadName = Thread.currentThread().getName();
            // test
            long count = rAtomicLong.addAndGet(1);
            System.out.printf("thread:{%s} >>> %d\n", threadName, count);
            return count;
        };

        try {
            ThreadPool.invoke(callable, 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ThreadPool.shutdown();
        }

    }

    @Test
    public void test2() {
        RLock rLock = redissonClient.getLock("WKRLock");
        Map<String, Integer> map = new HashMap() {{
            put("number", 0);
        }};

        Callable<Void> callable = () -> {
            rLock.lock();
            try {
                map.put("number", map.get("number") + 1);
            } finally {
                rLock.unlock();
            }
            return null;
        };

//        try {
//            ThreadPool.invoke(callable, 100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
            ThreadPool.shutdown();
//        }
        System.out.println("number=" + map.get("number"));
    }


    static class ThreadPool {
        private volatile static ThreadPoolExecutor executor;

        private static void initThreadPool() {
            executor = new ThreadPoolExecutor(128, 128, 0, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>());
        }

        public static <R> List<Future<R>> invoke(Callable<R> callable, int nRepeat) throws InterruptedException {
            return invoke(callable, nRepeat, 0, TimeUnit.SECONDS);
        }

        public static <R> List<Future<R>> invoke(Callable<R> callable, int nRepeat, long timeout, TimeUnit unit) throws InterruptedException {
            if (executor == null) {
                initThreadPool();
            }
            List<Callable<R>> callables = IntStream.range(0, nRepeat).boxed().map(i -> callable).collect(Collectors.toList());
            for (Callable<R> rCallable : callables) {
                System.out.println(rCallable.hashCode());
            }
            if (timeout <= 0) {
                return executor.invokeAll(callables);
            }
            return executor.invokeAll(callables, timeout, unit);
        }


        public static void shutdown() {
            if (executor != null && !executor.isShutdown()) {
                executor.shutdown();
            }
            executor = null;
        }
    }

}
