package com.dynamic.dao.mapper;

import com.common.dao.base.BaseMapper;
import com.dynamic.dao.entity.AppInfo;
import com.dynamic.dao.entity.AppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInfoMapper extends BaseMapper<AppInfo, AppInfoExample, Long> {
}