package com.example.timer;

import com.example.timer.service.TimerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimerApplicationTests {

    @Autowired
    private TimerService timerService;

    @Test
    public void contextLoads() throws InterruptedException {
        timerService.start("task-1", 8000L, TimeUnit.SECONDS);
        Thread.sleep(2000);
        timerService.cancel("task-1");
    }

    @Test
    public void test094058() {
        timerService.cancel("task-1");
    }

}
