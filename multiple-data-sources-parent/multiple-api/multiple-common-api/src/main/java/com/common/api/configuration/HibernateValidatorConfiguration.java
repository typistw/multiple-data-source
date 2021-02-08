package com.common.api.configuration;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/5/26 15:29
 * @Description:
 */
@Configuration
public class HibernateValidatorConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return  processor;
    }

    private static Validator validator(){
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                // 快速返回模式， 有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

}
