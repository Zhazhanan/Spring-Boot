package com.example.logback.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 * <br> Created by WangKun on 2019/04/18
 * <br> 动态修改日志级别
 *
 * @author wangkun
 **/
@RestController
@RequestMapping(value = "/api/logback")
public class LogbackController {

    public static final String PACKAGE_NAME = "-1";

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackController.class);

    /**
     * Description
     * <br> 更改日志级别。
     *
     * @param level       日志级别
     * @param packageName 包名、类名，默认值-1
     * @author WangKun
     * @date 2019/04/18
     **/
    @PutMapping(value = "/setLevel/v1")
    @ApiOperation(value = "设置日志级别", notes = "设置日志级别")
    public String updateLogbackLevel(@RequestParam(value = "level") String level,
                                     @RequestParam(value = "packageName", defaultValue = "-1") String packageName) throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (PACKAGE_NAME.equals(packageName)) {
            // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
            loggerContext.getLogger("root").setLevel(Level.toLevel(level));
        } else {
            loggerContext.getLogger(packageName).setLevel(Level.valueOf(level));
        }
        return "success";
    }


    /**
     * Description
     * <br> 获取日志级别
     *
     * @param packageName 包名、类名，默认值-1
     * @author WangKun
     * @date 2019/04/18
     **/
    @GetMapping(value = "/getLevel/v1")
    @ApiOperation(value = "获取日志级别", notes = "获取日志级别")
    public Level getLogbackLevel(@RequestParam(value = "packageName", defaultValue = PACKAGE_NAME) String packageName) throws Exception {
        Level level = null;
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (PACKAGE_NAME.equals(packageName)) {
            level = loggerContext.getLogger("root").getLevel();
        } else {
            level = loggerContext.getLogger(packageName).getLevel();
        }
        return level;
    }

    @GetMapping(value = "/get/v1")
    public void get(String par) {
        LOGGER.info("get::par = {}, level=info", par);
        LOGGER.debug("get::par = {}, level=debug", par);
    }

}
