package com.dynamic.configuration;

import com.common.constant.DataSourceConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  动态数据源上下文
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 16:27
 * @Description:
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(){
        /*
        *  默认数据源
        * */
        @Override
        protected String initialValue(){
            return DataSourceConstant.DS_DEV;
        }
    };

    /**
     *  数据源key集合
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * 切换数据源
     * @param keys
     */
    public static void setDataSourceKey(String keys){
        contextHolder.set(keys);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey(){
        return contextHolder.get();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey(){
        contextHolder.remove();
    }

    /**
     * 判断是否包含数据源
     * @param key
     * @return
     */
    public static boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys){
        return dataSourceKeys.addAll(keys);
    }
}
