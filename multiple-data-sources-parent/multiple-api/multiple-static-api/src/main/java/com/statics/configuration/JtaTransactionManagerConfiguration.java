package com.statics.configuration;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.common.constant.DataSourceConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 14:28
 * @Description:
 */
@Configuration
public class JtaTransactionManagerConfiguration {

    @Bean(name = DataSourceConstant.STATIC_DB_JTA_TRANSACTION_MANAGER)
    @Primary
    public JtaTransactionManager jtaTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
