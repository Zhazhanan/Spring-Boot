package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DistributedLockByZk implements Watcher {

    private ZooKeeper zk;
    private static final String LOCK_ROOT = "zklock";
    private String lockName;
    private String currnetLock;
    private String waitLock;
    private CountDownLatch countDownLatch;
    private int sessionTimeout = 30000;

    /*创建zk连接，创建锁的根节点*/
    public DistributedLockByZk(String zkAddres) {
        try {
            zk = new ZooKeeper(zkAddres, sessionTimeout, this);
            Stat exists = zk.exists(LOCK_ROOT, false);
            if (exists == null) {
                zk.create(LOCK_ROOT, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public boolean lock(String lockName) {
        this.lockName = lockName;
        if (tryLock()) {
            log.info("线程【" + Thread.currentThread().getName() + "】加锁（" + currnetLock + "）成功！");
            return true;
        } else {
            return;
        }
    }

    public boolean tryLock() {
        String split = "_lock_";
        this.lockName.contains(split);
    }
*/
    /*阻塞等待*/
    public boolean waitOtherLock(String waitLock, int sessionTimeout) {
        boolean isLock = false;
        String waitLockNode = LOCK_ROOT + "/" + waitLock;
        try {
            Stat exists = zk.exists(waitLockNode, true);
            if (exists != null) {
                log.info("线程【" + Thread.currentThread().getName() + "】锁（" + currnetLock + "）加锁失败，等待锁（" + waitLockNode + "释放...");
                countDownLatch = new CountDownLatch(1);
                isLock = countDownLatch.await(sessionTimeout, TimeUnit.MICROSECONDS);
                countDownLatch = null;
                if (isLock) {
                    log.info("线程【" + Thread.currentThread().getName() + "】锁（" + currnetLock + "）加锁成功！锁（" + waitLockNode + "）已释放");
                } else {
                    log.info("线程【" + Thread.currentThread().getName() + "】锁（" + currnetLock + "）加锁失败...");
                }
            } else {
                isLock = true;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isLock;
    }

    /*释放锁*/
    public void release() {
        try {
            Stat exists = zk.exists(currnetLock, true);
            if (exists != null) {
                zk.delete(currnetLock, -1);
                currnetLock = null;
                log.info("线程【" + Thread.currentThread().getName() + "】释放锁（" + currnetLock + "）");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
