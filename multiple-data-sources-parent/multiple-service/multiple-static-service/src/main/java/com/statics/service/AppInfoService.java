package com.statics.service;

import com.common.ResultEntity;
import com.common.vo.AddAppInfoReqVo;
import com.statics.dao.entity.AppInfo;

import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 15:03
 * @Description:
 */
public interface AppInfoService {

    AppInfo queryByAppId(String appId);

    List<AppInfo> queryListByAppId(String appId);

    ResultEntity insertAndQuery(AddAppInfoReqVo vo);

    Integer insert(AddAppInfoReqVo vo);
}
