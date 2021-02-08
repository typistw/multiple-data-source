package com.statics.dao.mapper.two;

import com.common.dao.base.BaseMapper;
import com.statics.dao.entity.BaseUser;
import com.statics.dao.entity.BaseUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseUserMapper extends BaseMapper<BaseUser, BaseUserExample, Integer> {
}