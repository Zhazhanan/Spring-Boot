package com.example.timer.controller;

import com.example.timer.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Description
 * <br> Created by WangKun on 2019/06/21
 * <br>
 **/
@RestController
public class TimerController {

    @Autowired
    private TimerService timerService;

    @GetMapping("/start")
    public void start() {
        timerService.start("task-1", 8000L, TimeUnit.SECONDS);
    }

    @GetMapping("/cancel")
    public void cancel() {
        timerService.cancel("task-1");
    }
}
