package com.example.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <br> Created by WangKun on 2019/06/20
 * <br> 分布式计时器
 **/
public abstract class JyCountdownTimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(JyCountdownTimerTask.class);

    private String timerName;
    private long duration;
    private ScheduledThreadPoolExecutor schedule;
    private RunnableScheduledFuture<Void> scheduledFuture;

    public JyCountdownTimerTask(String timerName, RunnableScheduledFuture<Void> scheduledFuture, ScheduledThreadPoolExecutor schedule) {
        this.timerName = timerName;
        this.schedule = schedule;
        this.scheduledFuture = scheduledFuture;
    }

    public JyCountdownTimerTask(String timerName, long duration, TimeUnit timeUnit, ScheduledThreadPoolExecutor schedule) {
        this.timerName = timerName;
        this.schedule = schedule;
        this.duration = timeUnit.toMillis(duration);
    }

    /**
     * Description
     * <br> 开始计时
     *
     * @author WangKun
     * @date 2019/06/20
     **/
    public RunnableScheduledFuture<Void> startCountdown() {
        try {
            scheduledFuture = (RunnableScheduledFuture<Void>) schedule.schedule(countDownTask(), duration, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.error("[开始计时失败]:: timerName = {} ", timerName, e);
        }
        LOGGER.info("开始计时[{}]::是否成功:{}", timerName, scheduledFuture);
        return scheduledFuture;
    }

    /**
     * Description
     * <br> 取消计时
     *
     * @author WangKun
     * @date 2019/06/20
     **/
    public boolean cancelCountdown() {
        // 已执行的任务取消会失败
        boolean removed = false;
        if (null != scheduledFuture && null != schedule) {
            removed = schedule.remove(scheduledFuture);
        }
        LOGGER.info("取消计时器[{}]::是否成功:{}", timerName, removed);
        return removed;
    }

    public abstract Runnable countDownTask();

}
