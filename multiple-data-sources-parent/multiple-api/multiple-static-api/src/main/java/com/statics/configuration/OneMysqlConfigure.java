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
@MapperScan(basePackages = {"com.statics.dao.mapper.one"}, sqlSessionFactoryRef = "db01SqlSessionFactory", sqlSessionTemplateRef = "db01SqlSessionTemplate")
public class OneMysqlConfigure {

    @Autowired
    @Qualifier("db01WriteDataSource")
    private DataSource dataSource;

    @Bean(name = "db01SqlSessionFactory")
    public SqlSessionFactory bSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/one/**/*.xml"));
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "db01SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db01SqlSessionFactory) {
        return new SqlSessionTemplate(db01SqlSessionFactory);
    }

    @Bean(name = DataSourceConstant.STATIC_DB_DB01_TRANSACTION)
    public DataSourceTransactionManager ontTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
