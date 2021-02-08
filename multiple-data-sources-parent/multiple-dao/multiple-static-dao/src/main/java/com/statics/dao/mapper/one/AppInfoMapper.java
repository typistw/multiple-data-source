package com.statics.dao.mapper.one;

import com.common.dao.base.BaseMapper;
import com.statics.dao.entity.AppInfo;
import com.statics.dao.entity.AppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInfoMapper extends BaseMapper<AppInfo, AppInfoExample, Long> {
}