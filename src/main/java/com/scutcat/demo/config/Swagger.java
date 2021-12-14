package com.scutcat.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author Jinbin
 * @date 2021/12/14 16:02
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger {

    @Bean(value = "outApi")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("华园猫崽项目接口说明文档")
                        .description("基于Swagger2和Knife4j实现的接口文档")
                        .termsOfServiceUrl("http://localhost:8080/")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("华园猫崽对外接口")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.scutcat.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
