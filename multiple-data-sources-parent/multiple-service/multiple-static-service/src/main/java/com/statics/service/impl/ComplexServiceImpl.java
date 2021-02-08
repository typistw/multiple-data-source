package com.statics.service.impl;

import com.common.ResultEntity;
import com.common.constant.DataSourceConstant;
import com.common.vo.AddUseAndAppInfoReqVo;
import com.statics.service.AppInfoService;
import com.statics.service.BaseUserService;
import com.statics.service.ComplexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 18:10
 * @Description:
 */
@Service
public class ComplexServiceImpl implements ComplexService {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private BaseUserService baseUserService;

    @Override
    @Transactional(value = DataSourceConstant.STATIC_DB_JTA_TRANSACTION_MANAGER, rollbackFor = Exception.class)
    public ResultEntity insertUserAndAppInfo(AddUseAndAppInfoReqVo vo) {
        appInfoService.insert(vo.getAddAppInfoReqVo());
        baseUserService.insert(vo.getAddUserReqVo());
        // 测试 事物回滚
        int x = 1/0;
        return ResultEntity.setSuccess();
    }
}
