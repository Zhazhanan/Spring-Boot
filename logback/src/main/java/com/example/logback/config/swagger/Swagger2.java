package com.example.logback.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

/**
 * Created by JieYue on 2018/9/15.
 * swagger 使用示例
 * https://blog.csdn.net/xupeng874395012/article/details/68946676
 *
 * @author xujunqi
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    @Bean
    public Docket inerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .directModelSubstitute(Timestamp.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**/v1/**"))
                .build()
                .groupName("反欺诈接口文档V1.0")
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("反欺诈系统 RESTful APIs")
                .description("反欺诈接口文档")
                .version("1.0")
                .build();
    }

}
