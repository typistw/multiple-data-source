package com.statics.service;

import com.common.ResultEntity;
import com.common.vo.AddUserReqVo;
import com.common.vo.QueryUserByMobileReqVo;
import com.common.vo.page.PageModel;
import com.statics.dao.entity.BaseUser;

import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:16
 * @Description:
 */
public interface BaseUserService {

    List<BaseUser> queryList(QueryUserByMobileReqVo vo);

    Integer insert(AddUserReqVo vo);

    ResultEntity insertAndQuery(AddUserReqVo vo);

    PageModel<BaseUser> queryPage(BaseUser user, PageModel<BaseUser> pageModel);

}
