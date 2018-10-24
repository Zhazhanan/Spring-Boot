package com.example.swaggerbootstrapuidemo.user.controller;

import com.example.swaggerbootstrapuidemo.autoConfig.dto.SysConfigDTO;
import com.example.swaggerbootstrapuidemo.autoConfig.service.SysConfigService;
import com.example.swaggerbootstrapuidemo.user.entity.User;
import com.example.swaggerbootstrapuidemo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "用户API")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "新增描述")
    public void add(User user) {
        userService.save(user);
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改", notes = "修改描述")
    public User update(User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/del")
    @ApiOperation(value = "删除", notes = "删除描述")
    public void delete(Long id) {
        Assert.notNull(id, "参数异常");
        userService.delete(id);
    }

    @GetMapping(value = "/getUser")
    @ApiOperation(value = "查询用户", notes = "查询用户描述")
    public User getUser(Long id) {
        Assert.notNull(id, "参数异常");
        return userService.get(id);
    }

    @GetMapping(value = "/getUsers")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表描述")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    })*/
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 404, message = "查询失败")
    })
//    public Page<User> getUser() {
//    public Page<User> getUser(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        return userService.getList(pageable);
//    }
    public List<User> getUser() {
        return userService.getList();
    }


    @RequestMapping(value = "/create/v1", method = RequestMethod.POST)
    public void create(@RequestBody SysConfigDTO sysConfigDTO) throws Exception {
        sysConfigService.insertSysConfig(sysConfigDTO);
    }

    /**
     * 修改一个业务对象
     *
     * @param sysConfigDTO
     * @return
     */
    @ApiOperation(nickname = "create", value = "create SysConfigDTO", notes = "sysConfigDTO")
    public void update(@RequestBody SysConfigDTO sysConfigDTO) throws Exception {
        sysConfigService.updateSysConfig(sysConfigDTO);
    }

}
