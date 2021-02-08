package com.dynamic.controller;

import com.common.ResultEntity;
import com.dynamic.service.AppInfoService;
import com.common.vo.AddAppInfoReqVo;
import com.common.vo.QueryAppProviderReqVo;
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
@RequestMapping(value = "dynamic/app")
@Api(tags = "动态数据源")
public class AppProviderInfoController {

    @Autowired
    private AppInfoService appProviderService;

    @PostMapping(value = "queryByAppId")
    @ApiOperation(value = "查询app信息")
    public ResultEntity queryAppProvider(@Valid @RequestBody QueryAppProviderReqVo vo){
        return  ResultEntity.setSuccess(appProviderService.queryByAppId(vo.getAppId()));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "添加")
    public ResultEntity addAppProvider(@Valid @RequestBody AddAppInfoReqVo vo){
//        AppProvider app = new AppProvider();
//        app.setAppId(vo.getAppId());
//        app.setCreateTime(System.currentTimeMillis());
        return null;
    }

}
