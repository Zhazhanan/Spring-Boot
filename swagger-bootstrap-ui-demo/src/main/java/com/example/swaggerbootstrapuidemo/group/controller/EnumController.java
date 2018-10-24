/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.example.swaggerbootstrapuidemo.group.controller;

import com.example.swaggerbootstrapuidemo.common.ErrorCode;
import com.example.swaggerbootstrapuidemo.common.Language;
import com.example.swaggerbootstrapuidemo.common.Result;
import com.example.swaggerbootstrapuidemo.group.model.EnumDomain;
import com.example.swaggerbootstrapuidemo.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "枚举API")
@RestController
@RequestMapping("/api/enum")
public class EnumController {

    @PostMapping("/actEumList")
    @ApiOperation(value = "简单枚举类型", notes = "简单枚举")
    public Result<User> actEumList(Language language) {
        Result<User> r = new Result<>();
        User rp = new User();
        rp.setName(language.name());
        r.setData(rp);
        return r;
    }

    @PostMapping("/actEumsList")
    @ApiOperation(value = "复杂枚举类型", notes = "复杂枚举")
    public Result<User> actEumsList(ErrorCode errorCode) {
        Result<User> r = new Result<User>();
        User rp = new User();
        rp.setName(errorCode.getMsg() + "--" + errorCode.getCode());
        r.setData(rp);
        return r;
    }

    @PostMapping("/actEumssList")
    @ApiOperation(value = "复杂枚举类型-domain", notes = "复杂枚举-domain")
    public Result<EnumDomain> actEumssList(EnumDomain ed) {
        Result<EnumDomain> r = new Result<>();
        r.setData(ed);
        return r;
    }
}
