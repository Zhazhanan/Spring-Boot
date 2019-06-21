package com.example.timer.task;

import com.example.timer.JyCountdownTimerTask;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <br> Created by WangKun on 2019/06/21
 * <br>
 **/
public class LeaveNotiyTask extends JyCountdownTimerTask {
    public LeaveNotiyTask(String timerName, long duration, TimeUnit timeUnit, ScheduledThreadPoolExecutor schedule) {
        super(timerName, duration, timeUnit, schedule);
    }

    public LeaveNotiyTask(String timerName, RunnableScheduledFuture<Void> scheduledFuture, ScheduledThreadPoolExecutor schedule) {
        super(timerName, scheduledFuture, schedule);
    }

    @Override
    public Runnable countDownTask() {
        System.out.println("--------------------");
        return () -> {
            // TODO: 2019-06-20 08:33 业务逻辑
            System.out.println("LeaveNotiyTask countdown");
        };
    }
}
