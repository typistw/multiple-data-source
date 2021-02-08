package com.common.constant;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 18:14
 * @Description:
 */
public class DataSourceConstant {

    private DataSourceConstant(){}

    // 动态数据源
    public static final String DS_DEV = "dsDev";
    public static final String DS_TEST = "dsTest";

    // 静态数据源
    public static final String STATIC_DB_DB01_TRANSACTION = "db01PlatformTransactionManager";
    public static final String STATIC_DB_DB02_TRANSACTION = "db02PlatformTransactionManager";
    /**
     *  jat 事物管理
     */
    public static final String STATIC_DB_JTA_TRANSACTION_MANAGER = "staticsJtaTransactionManager";

}
