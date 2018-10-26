package com.example.rocketmq.config.exception;

/**
 * @author WangKun
 * @create 2018-09-03
 * @desc
 **/
public class MQException extends RuntimeException {

    public MQException() {
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(Throwable cause) {
        super(cause);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }
}
