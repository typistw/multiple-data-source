package com.dynamic.service.impl;

import com.common.ResultEntity;
import com.common.annotation.SelectDataSource;
import com.common.constant.DataSourceConstant;
import com.common.enums.ErrCodeEnums;
import com.common.exception.CommonException;
import com.common.vo.AddAppInfoReqVo;
import com.dynamic.dao.entity.AppInfo;
import com.dynamic.dao.entity.AppInfoExample;
import com.dynamic.dao.mapper.AppInfoMapper;
import com.dynamic.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 15:05
 * @Description:
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    @SelectDataSource(DataSourceConstant.DS_DEV)
    public AppInfo queryByAppId(String appId) {
        AppInfoExample example = new AppInfoExample();
        example.createCriteria().andAppIdEqualTo(appId);
        List<AppInfo> list = appInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw  new CommonException(ErrCodeEnums.FAILURE.getCode(), "查无信息");
        }

        return list.get(0);
    }

    @Override
    public ResultEntity insertAndQuery(AddAppInfoReqVo vo) {
        return null;
    }
}
