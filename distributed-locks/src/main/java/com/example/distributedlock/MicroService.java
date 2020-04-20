package com.example.distributedlock;

import com.example.utils.DistributedLockByCurator;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MicroService {

    @Autowired
    DistributedLockByCurator distributedLockByZookeeper;

    @Autowired
    CuratorFramework cruatorFramework;

    /*通过zk获取分布式锁*/
    public Boolean getLockByZK(String path) {
        Boolean flag;
        distributedLockByZookeeper.acquireDistributedLock(path);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag = distributedLockByZookeeper.releaseDistributedLock(path);
        }
        return flag;
    }

    /*通过curator获取分布式锁*/
    public Boolean getLockByCurator(String path) {
        Boolean flag;
        distributedLockByZookeeper.acquireDistributedLock(path);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag = distributedLockByZookeeper.releaseDistributedLock(path);
        }
        return flag;
    }

    /*通过curator获取分布式锁*/
    public Boolean getLockByCurator2(String lockPath) {
        Boolean flag = false;
        InterProcessMutex mutex = new InterProcessMutex(cruatorFramework, lockPath);
        try {
            flag = mutex.acquire(0, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
