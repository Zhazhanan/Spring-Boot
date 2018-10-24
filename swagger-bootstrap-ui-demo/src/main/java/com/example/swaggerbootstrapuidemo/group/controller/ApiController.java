/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.example.swaggerbootstrapuidemo.group.controller;

import com.example.swaggerbootstrapuidemo.common.Result;
import com.example.swaggerbootstrapuidemo.group.model.ListDomain;
import com.example.swaggerbootstrapuidemo.group.model.SendUserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "API注释")
@RequestMapping(value = "/api/aci")
public class ApiController {

    @PostMapping("/sendUser")
    @ApiOperation(value = "用户注册", notes = "填写用户信息注册用户")
    public Result<SendUserRequest> requestRest(@RequestBody SendUserRequest sendUserRequest) {
        Result<SendUserRequest> requestRest = new Result<>();
        sendUserRequest.setEmail("chinesepnadahuha@yahoo.com");
        requestRest.setData(sendUserRequest);
        return requestRest;
    }

    @PostMapping("/listDomain")
    @ApiOperation(value = "listDomain", notes = "针对属性是集合的example")
    public Result<ListDomain> listDomain(ListDomain sendUserRequest) {
        Result<ListDomain> requestRest = new Result<>();
        requestRest.setData(sendUserRequest);
        return requestRest;
    }

    @DeleteMapping("/requestHeader")
    @ApiOperation(value = "string-RequestHeader参数类型请求接口")
    public Result<String> requestHeader(@RequestHeader(name = "key") String key) {
        Result<String> r = new Result<>();
        r.setData(key);
        r.setSuccess(false);
        return r;
    }

    @PostMapping("/requestFile")
    @ApiOperation(value = "多file参数传递")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "MultipartFile", allowMultiple = true)})
    public Result<String> mulformAndEnumAndfile(String val, MultipartFile file) {
        Result<String> r = new Result<>();
        r.setData(val);
        return r;
    }
}
