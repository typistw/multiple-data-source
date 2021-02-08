package com.statics.configuration;

import com.common.utils.PropertiesSqlConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class StaticsDataSourceConfigure {
    private static final Logger logger = LoggerFactory.getLogger(StaticsDataSourceConfigure.class);

//    @Value("${mysql.datasource.type:com.alibaba.druid.pool.DruidDataSource}")
//    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "db01WriteDataSource")
    public DataSource tspWriteDataSource(Environment env) {
        logger.info("static: init ds01 data source ...");
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = PropertiesSqlConnectionUtil.build(env, "spring.datasource.ds01.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("db01WriteDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }

    @Bean(name = "db02WriteDataSource")
    public DataSource agentWriteDataSource(Environment env) {
        logger.info("static: init ds02 data source ...");
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = PropertiesSqlConnectionUtil.build(env, "spring.datasource.ds02.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("db02WriteDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }
}
