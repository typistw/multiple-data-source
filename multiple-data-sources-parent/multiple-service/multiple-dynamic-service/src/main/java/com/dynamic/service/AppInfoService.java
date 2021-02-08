package com.dynamic.service;

import com.common.ResultEntity;
import com.common.vo.AddAppInfoReqVo;
import com.dynamic.dao.entity.AppInfo;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 15:03
 * @Description:
 */
public interface AppInfoService {

    AppInfo queryByAppId(String appId);

    ResultEntity insertAndQuery(AddAppInfoReqVo vo);
}
