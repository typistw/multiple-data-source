package com.statics.service;

import com.common.ResultEntity;
import com.common.vo.AddUseAndAppInfoReqVo;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 18:05
 * @Description:
 */
public interface ComplexService {

    ResultEntity insertUserAndAppInfo(AddUseAndAppInfoReqVo vo);
}
