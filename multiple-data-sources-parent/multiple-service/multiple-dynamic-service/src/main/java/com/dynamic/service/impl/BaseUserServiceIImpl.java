package com.dynamic.service.impl;

import com.common.annotation.SelectDataSource;
import com.common.constant.DataSourceConstant;
import com.common.vo.QueryUserByMobileReqVo;
import com.dynamic.dao.entity.BaseUser;
import com.dynamic.dao.entity.BaseUserExample;
import com.dynamic.dao.mapper.BaseUserMapper;
import com.dynamic.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:17
 * @Description:
 */
@Service
public class BaseUserServiceIImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    @SelectDataSource(DataSourceConstant.DS_TEST)
    public List<BaseUser> queryList(QueryUserByMobileReqVo vo) {
        BaseUserExample example = new BaseUserExample();
        List<String> mobileList = new ArrayList<>();
        for (int i = 0; i < vo.getMobileList().size(); i++) {
            mobileList.add(vo.getMobileList().get(i).getMobile());
        }

        example.createCriteria().andMobileIn(mobileList);
        return baseUserMapper.selectByExample(example);
    }
}
