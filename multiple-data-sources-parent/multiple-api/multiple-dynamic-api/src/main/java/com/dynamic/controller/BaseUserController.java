package com.dynamic.controller;

import com.common.ResultEntity;
import com.dynamic.service.BaseUserService;
import com.common.vo.QueryUserByMobileReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:25
 * @Description:
 */
@RestController
@RequestMapping(value = "dynamic/user")
@Api(tags = "动态数据源")
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping(value = "queryUserByMobile")
    @ApiOperation(value = "查询用户")
    public ResultEntity queryUserByMobile(@Valid @RequestBody QueryUserByMobileReqVo vo){
      return  ResultEntity.setSuccess(baseUserService.queryList(vo));
    }

}
