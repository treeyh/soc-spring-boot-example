package com.treeyh.example.springboot.api;

import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.model.result.ReturnResult;
import com.treeyh.example.springboot.api.model.base.req.UserInfoReq;
import com.treeyh.example.springboot.api.model.base.resp.UserInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author TreeYH
 * @version 1.0
 * @description 示例接口定义
 * @create 2019-05-21 09:52
 */
@Api(value = "示例用户服务接口", description = "示例用户服务接口")
@RequestMapping(value = "/api/v1")
public interface UserInfoFacade {


    /**
     * 新建用户
     *
     * @param userReq
     * @return
     */
    @ApiOperation(value = "新建用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes = "新建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userReq", value = "用户", required = true, dataType = "UserReq", paramType = "body")
    })
    @RequestMapping(value = {"/user"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<UserInfoResp> createUser(@RequestBody UserInfoReq userReq);


    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/user/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult queryById(@PathVariable("id") Long id);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/user/{id}"},
            method = {RequestMethod.DELETE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult deleteById(@PathVariable("id") Long id);


    /**
     * 更新用户信息
     *
     * @param id
     * @param userReq
     * @return
     */
    @ApiOperation(value = "更新用户信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "userReq", value = "对象", required = true, dataType = "UserReq", paramType = "body")
    })
    @RequestMapping(value = {"/user/{id}"},
            method = {RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<UserInfoReq> updateById(@PathVariable("id") Long id, @RequestBody UserInfoReq userReq);


    /**
     * 分页查询用户
     *
     * @param name
     * @param status
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = {"/user"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<PageResult<UserInfoResp>> queryByPage(@RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "status", required = false) Integer status,
                                                              @RequestParam("page") Long page,
                                                              @RequestParam("size") Long size);

}
