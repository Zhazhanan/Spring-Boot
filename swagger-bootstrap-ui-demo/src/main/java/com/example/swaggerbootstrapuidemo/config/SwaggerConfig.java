package com.example.swaggerbootstrapuidemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangKun
 * @create 2018-05-29 上午 11:47
 * @desc
 **/
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket open_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/users/**"))
                .build()
                .groupName("WEB对外接口文档V4.4")
                .pathMapping("/")
                .globalOperationParameters(setHeaderToken())
                .apiInfo(apiInfo("WEB接口文档V4.4及之前", "文档中可以查询及测试接口调用参数和结果", "4.4"));
    }

    @Bean
    public Docket iner_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .groupName("WEB内部接口文档V4.4.4")
                .pathMapping("/")
                .apiInfo(apiInfo("WEB接口文档V4.4.4", "文档中可以查询及测试接口调用参数和结果", "4.4.4"));
    }

    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .version(version)
                .contact(new Contact("WK", "https://github.com/Zhazhanan/", "chinesepandahuha@yahoo.com"))
                .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("认证信息").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }
}
