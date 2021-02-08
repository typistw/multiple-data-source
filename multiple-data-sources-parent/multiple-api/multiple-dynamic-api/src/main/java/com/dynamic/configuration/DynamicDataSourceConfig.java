package com.dynamic.configuration;

import com.common.constant.DataSourceConstant;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 15:32
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.dynamic.dao.mapper")
public class DynamicDataSourceConfig {

    private final static Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfig.class);

    @Bean(name = DataSourceConstant.DS_DEV)
    @ConfigurationProperties(prefix = "spring.datasource.ds01")
    public DataSource dataSourceDev(){
        logger.info("dynamic: init ds01 data source ...");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceConstant.DS_TEST)
    @ConfigurationProperties(prefix = "spring.datasource.ds02")
    public DataSource dataSourceTest(){
        logger.info("dynamic: init ds01 data source ...");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDatasource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceConstant.DS_DEV, dataSourceDev());
        dataSourceMap.put(DataSourceConstant.DS_TEST, dataSourceTest());

        dynamicDatasource.setDefaultDataSource(dataSourceDev());
        dynamicDatasource.setDataSources(dataSourceMap);

        return dynamicDatasource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        sessionFactoryBean.setDataSource(dynamicDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
