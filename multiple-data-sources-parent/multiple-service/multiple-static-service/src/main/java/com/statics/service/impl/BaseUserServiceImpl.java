package com.statics.service.impl;

import com.common.ResultEntity;
import com.common.constant.DataSourceConstant;
import com.common.utils.ConditionBuildUtil;
import com.common.vo.AddUserReqVo;
import com.common.vo.QueryUserByMobileReqVo;
import com.common.vo.page.PageModel;
import com.statics.dao.entity.BaseUser;
import com.statics.dao.entity.BaseUserExample;
import com.statics.dao.mapper.two.BaseUserMapper;
import com.statics.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:17
 * @Description:
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public List<BaseUser> queryList(QueryUserByMobileReqVo vo) {
        BaseUserExample example = new BaseUserExample();
        List<String> mobileList = new ArrayList<>();
        for (int i = 0; i < vo.getMobileList().size(); i++) {
            mobileList.add(vo.getMobileList().get(i).getMobile());
        }

        example.createCriteria().andMobileIn(mobileList);
        return baseUserMapper.selectByExample(example);
    }

    @Override
    public Integer insert(AddUserReqVo vo) {
        BaseUser user = new BaseUser();
        user.setMobile(vo.getMobile());
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setProviderId(1);
        return baseUserMapper.insertSelective(user);
    }

    @Override
    @Transactional(value = DataSourceConstant.STATIC_DB_DB02_TRANSACTION, rollbackFor = Exception.class)
    public ResultEntity insertAndQuery(AddUserReqVo vo) {
        BaseUser user = new BaseUser();
        user.setMobile(vo.getMobile());
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setProviderId(1);
        baseUserMapper.insertSelective(user);

        BaseUser queryUser  = baseUserMapper.selectByPrimaryKey(user.getId());
        return ResultEntity.setSuccess(queryUser);
    }

    @Override
    public PageModel<BaseUser> queryPage(BaseUser user, PageModel<BaseUser> pageModel) {
        BaseUserExample example = ConditionBuildUtil.buildCondition(new BaseUserExample(), BaseUser.class, user);
        ConditionBuildUtil.parsePageParam(pageModel, example);
        List<BaseUser> list = baseUserMapper.selectByExample(example);
        Long totalCount = baseUserMapper.countByExample(example);

        pageModel.setData(list);
        pageModel.setTotalCount(totalCount);

        return pageModel;
    }
}
