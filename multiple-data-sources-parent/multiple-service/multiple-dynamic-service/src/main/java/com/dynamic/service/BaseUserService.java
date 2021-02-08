package com.dynamic.service;

import com.common.vo.QueryUserByMobileReqVo;
import com.dynamic.dao.entity.BaseUser;

import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:16
 * @Description:
 */
public interface BaseUserService {

    List<BaseUser> queryList(QueryUserByMobileReqVo vo);

}
