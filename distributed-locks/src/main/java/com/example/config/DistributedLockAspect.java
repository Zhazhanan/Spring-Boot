package com.example.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.utils.CloseableUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Aspect
public class DistributedLockAspect{

    @Autowired
    CuratorFramework cruatorFramework;

    @Pointcut("@annotation(com.**.**.DistributedLock")
    public void methodAspect(){};

    @Around("methodAspect()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Exception{

        String lockPath = "/opt/zookeeper/lock";
        InterProcessMutex mutex = new InterProcessMutex(cruatorFramework,lockPath);
        try{
            boolean locked = mutex.acquire(0, TimeUnit.SECONDS);
            if(!locked){
                return null;
            }else{
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mutex.release();
        }
        return null;
    }

    @PreDestroy
    public void destroy(){
        CloseableUtils.closeQuietly(cruatorFramework);
    }
}
