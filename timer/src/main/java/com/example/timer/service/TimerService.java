package com.example.timer.service;

import com.example.timer.task.LeaveNotiyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <br> Created by WangKun on 2019/06/20
 * <br>
 **/
@Service
@Scope("prototype")
public class TimerService {
    @Autowired
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    ThreadLocal<ConcurrentHashMap<String, RunnableScheduledFuture<Void>>> THREAD_LOCAL = new ThreadLocal<>();

    private ConcurrentHashMap<String, RunnableScheduledFuture<Void>> concurrentHashMap = new ConcurrentHashMap();

    public void start(String timerName, long duration, TimeUnit timeUnit) {
        LeaveNotiyTask task = new LeaveNotiyTask(timerName, duration, timeUnit, scheduledThreadPoolExecutor);
        RunnableScheduledFuture<Void> future = task.startCountdown();
        concurrentHashMap.put(timerName, future);
        THREAD_LOCAL.set(concurrentHashMap);
        System.out.println(concurrentHashMap.size() + "====" + concurrentHashMap.hashCode() + "=========");
    }

    public void cancel(String timerName) {
        ConcurrentHashMap<String, RunnableScheduledFuture<Void>> map = THREAD_LOCAL.get();
        RunnableScheduledFuture<Void> voidRunnableScheduledFuture = map.get(timerName);

        System.out.println(concurrentHashMap.size() + "====" + concurrentHashMap.hashCode() + "=========" + voidRunnableScheduledFuture);
        if (null != voidRunnableScheduledFuture) {
            LeaveNotiyTask task = new LeaveNotiyTask(timerName, voidRunnableScheduledFuture, scheduledThreadPoolExecutor);
            task.cancelCountdown();
        }
    }
}
