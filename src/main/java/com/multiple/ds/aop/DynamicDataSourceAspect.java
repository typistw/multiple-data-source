package com.multiple.ds.aop;

import com.multiple.ds.annotation.SelectDataSource;
import com.multiple.ds.configuration.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before("@annotation(dataSource)")
    public void switchDataSource(JoinPoint point, SelectDataSource dataSource){
        if(!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())){
            log.info("DataSource [{}] doesn't exist, use default DataSource [{}] ", dataSource.value());
        }else {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
           log.info("Switch DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                    + "] in Method [" + point.getSignature() + "]");
        }
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, SelectDataSource dataSource){
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }
}
