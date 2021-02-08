package com.statics.controller;

import com.common.ResultEntity;
import com.common.vo.AddUseAndAppInfoReqVo;
import com.statics.service.ComplexService;
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
 * @Date: 2020/7/3 17:48
 * @Description:
 */
@RestController
@RequestMapping(value = "static/complex")
@Api(value = "静态数据源")
public class ComplexController {

    @Autowired
    private ComplexService complexService;

    /**
     *  测试多事物管理
     * @param vo
     * @return
     */
    @PostMapping(value = "addUserAndAppInfo")
    @ApiOperation(value = "添加用户&APP信息")
    public ResultEntity addUserAndAppInfo(@Valid @RequestBody AddUseAndAppInfoReqVo vo){
        return complexService.insertUserAndAppInfo(vo);
    }

}
