/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.example.swaggerbootstrapuidemo.group.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "基础类型API")
@RestController
@RequestMapping("/api/single")
public class SingleTypeController {

    @GetMapping(value = "/boolean")
    @ApiOperation(value = "Boolean类型", notes = "返回Boolean类型")
    public Boolean booleanapi() {
        return false;
    }

    @GetMapping(value = "/int")
    public int integerapi() {
        return 1;
    }

    @GetMapping(value = "/string")
    @ApiIgnore
    public String strapi() {
        return "1";
    }
}
