package com.statics.controller;

import com.common.ResultEntity;
import com.common.vo.AddAppInfoReqVo;
import com.common.vo.QueryAppProviderReqVo;
import com.statics.service.AppInfoService;
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
 * @Date: 2020/7/2 15:28
 * @Description:
 */
@RestController
@RequestMapping(value = "static/app")
@Api(tags = "静态数据源")
public class AppProviderInfoController {

    @Autowired
    private AppInfoService appProviderService;

    @PostMapping(value = "queryByAppId")
    @ApiOperation(value = "查询app信息")
    public ResultEntity queryAppProvider(@Valid @RequestBody QueryAppProviderReqVo vo){
        return  ResultEntity.setSuccess(appProviderService.queryByAppId(vo.getAppId()));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "添加APP")
    public ResultEntity addAppProvider(@Valid @RequestBody AddAppInfoReqVo vo){
        return appProviderService.insertAndQuery(vo);
    }

}
