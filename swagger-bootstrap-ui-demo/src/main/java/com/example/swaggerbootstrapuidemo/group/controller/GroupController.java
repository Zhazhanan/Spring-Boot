/*
 * Copyright (C) 2017 Zhejiang BYCDAO Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.bycdao.com.
 * Developer Web Site: http://open.bycdao.com.
 */

package com.example.swaggerbootstrapuidemo.group.controller;

import com.example.swaggerbootstrapuidemo.common.Result;
import com.example.swaggerbootstrapuidemo.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分组测试")
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @PostMapping("/xxx")
    @ApiOperation(value = "ModelAttribute", notes = "ModelAttribute类型参数", tags = {"API注释"})
    public Result reqbody1(@ModelAttribute User user) {
        Result<User> result = new Result<>();
        result.setData(user);
        return result;
    }

    @PostMapping("/reqbody2")
    @ApiOperation(value = "ModelAttribute", notes = "ModelAttribute类型参数")
    public Result reqbody2(@ModelAttribute User reqEntity) {
        Result<User> result = new Result<>();
        result.setData(reqEntity);
        return result;
    }

    @PostMapping("/reqbody3")
    @ApiOperation(value = "ModelAttribute3", notes = "ModelAttribute类型参数3")
    public String reqbody3(@RequestParam(value = "name") String name) {
        return name;
    }

}
