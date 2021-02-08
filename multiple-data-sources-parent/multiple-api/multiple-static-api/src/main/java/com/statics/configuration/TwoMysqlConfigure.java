package com.statics.configuration;

import com.common.constant.DataSourceConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 16:56
 * @Description:
 */
@AutoConfigureAfter(StaticsDataSourceConfigure.class)
@Configuration
@MapperScan(basePackages = {"com.statics.dao.mapper.two"}, sqlSessionFactoryRef = "db02SqlSessionFactory", sqlSessionTemplateRef = "db02SqlSessionTemplate")
public class TwoMysqlConfigure {

    @Autowired
    @Qualifier("db02WriteDataSource")
    private DataSource dataSource;

    @Bean(name = "db02SqlSessionFactory")
    public SqlSessionFactory bSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/two/**/*.xml"));
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "db02SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db02SqlSessionFactory) {
        return new SqlSessionTemplate(db02SqlSessionFactory);
    }

    @Bean(name = DataSourceConstant.STATIC_DB_DB02_TRANSACTION)
    public DataSourceTransactionManager ontTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
