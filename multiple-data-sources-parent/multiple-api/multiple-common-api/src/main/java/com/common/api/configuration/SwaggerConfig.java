package com.common.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value = "${swagger.enable:false}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        //http://ip地址:端口/项目名/swagger-ui.html#/
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("多数据源测试")
                .description("多数据源 swagger RESTful APIs......")
                .version("1.0.0")
                .contact(new Contact("多数据源", "http://www.xxxx.com/", "xxxx@xxx.com"))
                .license("The Apache License")
                .licenseUrl("http://www.xxx.com/")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/").enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}
