package com.dynamic.dao.mapper;

import com.common.dao.base.BaseMapper;
import com.dynamic.dao.entity.BaseUser;
import com.dynamic.dao.entity.BaseUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseUserMapper extends BaseMapper<BaseUser, BaseUserExample, Long> {
}