package com.statics.controller;

import com.common.ResultEntity;
import com.common.vo.AddUserReqVo;
import com.common.vo.QueryUserByMobileReqVo;
import com.common.vo.QueryUserReqVo;
import com.common.vo.page.PageModel;
import com.statics.dao.entity.BaseUser;
import com.statics.service.BaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@RequestMapping(value = "static/user")
@Api(tags = "静态数据源")
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping(value = "queryUserByMobile")
    @ApiOperation(value = "查询用户通过手机号")
    public ResultEntity queryUserByMobile(@Valid @RequestBody QueryUserByMobileReqVo vo){
      return  ResultEntity.setSuccess(baseUserService.queryList(vo));
    }

    @PostMapping(value = "queryUser")
    @ApiOperation(value = "查询用户")
    public ResultEntity queryUser(@Valid @RequestBody QueryUserReqVo vo){
        PageModel<BaseUser> pageModel = new PageModel<>();
        if(vo.getPage() <= 0){
            vo.setPage(1);
        }
        pageModel.setCurrentPage(vo.getPage());
        BaseUser user = new BaseUser();
        BeanUtils.copyProperties(vo, user);
      return  ResultEntity.setSuccess(baseUserService.queryPage(user, pageModel));
    }

    @PostMapping(value = "addUser")
    @ApiOperation(value = "添加用户")
    public ResultEntity addUser(@Valid @RequestBody AddUserReqVo vo){
        return baseUserService.insertAndQuery(vo);
    }

}
