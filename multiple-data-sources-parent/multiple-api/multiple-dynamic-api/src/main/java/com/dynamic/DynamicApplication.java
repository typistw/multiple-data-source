package com.dynamic;

import com.common.utils.ServiceBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.dynamic", "com.common.api"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DynamicApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DynamicApplication.class, args);
		ServiceBeanUtil.setApplicationContext(applicationContext);
	}
}