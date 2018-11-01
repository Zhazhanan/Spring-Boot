package com.example.websoket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @DES: ${DESCRIPTION}
 * @Author: wangK
 * @Date: Created in 23:28 2018/11/1
 */
@Service
public class CheckCenterService {

    @Scheduled(fixedRate = 1000)
    public void send() {
        try {
            WebSocketServer.sendInfo(LocalDateTime.now().toString(), "1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
