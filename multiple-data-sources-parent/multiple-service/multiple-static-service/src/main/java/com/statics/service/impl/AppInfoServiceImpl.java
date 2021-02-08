package com.statics.service.impl;

import com.common.ResultEntity;
import com.common.constant.DataSourceConstant;
import com.common.enums.ErrCodeEnums;
import com.common.exception.CommonException;
import com.common.vo.AddAppInfoReqVo;
import com.statics.dao.entity.AppInfo;
import com.statics.dao.entity.AppInfoExample;
import com.statics.dao.mapper.one.AppInfoMapper;
import com.statics.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public AppInfo queryByAppId(String appId) {
        List<AppInfo> list = this.queryListByAppId(appId);
        if (CollectionUtils.isEmpty(list)){
            throw  new CommonException(ErrCodeEnums.FAILURE.getCode(), "查无信息");
        }

        return list.get(0);
    }

    @Override
    public List<AppInfo> queryListByAppId(String appId) {
        AppInfoExample example = new AppInfoExample();
        example.createCriteria().andAppIdEqualTo(appId);
        return  appInfoMapper.selectByExample(example);
    }

    @Override
    @Transactional(value = DataSourceConstant.STATIC_DB_DB01_TRANSACTION, rollbackFor = Exception.class)
    public ResultEntity insertAndQuery(AddAppInfoReqVo vo) {
        AppInfo info = new AppInfo();
        info.setAppId(vo.getAppId());
        info.setAppSecret(vo.getAppKey());
        info.setCreateTime(System.currentTimeMillis());
        info.setUpdateTime(System.currentTimeMillis());
        info.setOrderChannelId((byte)1);
        appInfoMapper.insertSelective(info);

        List<AppInfo> list = this.queryListByAppId(vo.getAppId());

        return ResultEntity.setSuccess(list);
    }

    @Override
    public Integer insert(AddAppInfoReqVo vo) {
        AppInfo info = new AppInfo();
        info.setAppId(vo.getAppId());
        info.setAppSecret(vo.getAppKey());
        info.setCreateTime(System.currentTimeMillis());
        info.setUpdateTime(System.currentTimeMillis());
        info.setOrderChannelId((byte)1);
        return  appInfoMapper.insertSelective(info);
    }
}
