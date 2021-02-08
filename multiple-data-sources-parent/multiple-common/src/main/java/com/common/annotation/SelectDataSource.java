package com.common.annotation;

import java.lang.annotation.*;

/**
 *  动态注解数据源
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 16:54
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SelectDataSource {

    /**
     * 数据源key值
     * @return
     */
    String value();
}
