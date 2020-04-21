package com.example.distributedlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MicroService {

    @Autowired
    CuratorFramework cruatorFramework;

    /**
     * 通过curator获取分布式锁
     */
    public void getLockByCurator(InterProcessLock lock) {
        try {
            if (lock.acquire(10, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " has the lock");
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
                System.out.println(Thread.currentThread().getName() + " releasing the lock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
