package com.example.redisredlock;

/**
 * @author WangKun
 * @create 2018-11-29 下午 5:19
 * @desc
 **/
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
