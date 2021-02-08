package com.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ServiceBeanUtil {

    private static ApplicationContext appCtx;

    public static void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        appCtx = ctx;
    }

    /**
     * @param entityClass
     * @return
     */
    public static <T> T getBean(Class<T> entityClass) {
        return appCtx.getBean(entityClass);
    }

    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
}

