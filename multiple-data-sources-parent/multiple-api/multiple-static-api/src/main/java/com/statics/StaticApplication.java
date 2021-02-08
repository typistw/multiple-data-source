package com.statics;

import com.common.utils.ServiceBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.statics", "com.common.api"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StaticApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StaticApplication.class, args);
		ServiceBeanUtil.setApplicationContext(applicationContext);
	}
}