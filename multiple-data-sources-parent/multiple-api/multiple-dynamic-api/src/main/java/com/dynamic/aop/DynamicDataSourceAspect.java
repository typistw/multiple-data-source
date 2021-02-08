package com.dynamic.aop;

import com.common.annotation.SelectDataSource;
import com.dynamic.configuration.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态数据源处理器
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 16:58
 * @Description:
 */
@Aspect
@Order(-1) // 该切面应当先于 @Transactional 执行
@Component
@Slf4j
public class DynamicDataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(dataSource)")
    public void switchDataSource(JoinPoint point, SelectDataSource dataSource){
        if(!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())){
            LOGGER.info("DataSource [{}] doesn't exist, use default DataSource [{}] ", dataSource.value());
        }else {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
           LOGGER.info("Switch DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                    + "] in Method [" + point.getSignature() + "]");
        }
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, SelectDataSource dataSource){
        DynamicDataSourceContextHolder.clearDataSourceKey();
        LOGGER.info("Restore DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }
}
