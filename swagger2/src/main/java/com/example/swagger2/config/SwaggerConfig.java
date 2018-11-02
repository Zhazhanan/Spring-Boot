package com.example.swagger2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author WangKun
 * @create 2018-05-29 上午 11:47
 * @desc
 **/
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Value("${swagger.basePackage}")
    private String basePackage;

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                /*.securitySchemes(asList(
                        new OAuth(
                                "petstore_auth",
                                asList(new AuthorizationScope("write_pets", "modify pets in your account"),
                                        new AuthorizationScope("read_pets", "read your pets")),
                                Arrays.<GrantType>asList(new ImplicitGrant(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog"), "tokenName"))
                        ),
                        new ApiKey("api_key", "api_key", "header")
                ))*/
//                .securitySchemes(asList(new BasicAuth("test")))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
//                .paths(Predicates.and(ant("/**"), Predicates.not(ant("/error")), Predicates.not(ant("/management/**")), Predicates.not(ant("/management*"))))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("稽核系统接口文档demo")
                .description("稽核系统接口文档")
                .version("1.0.0")
                .contact(new Contact("WK", "https://github.com/Zhazhanan/", "chinesepandahuha@yahoo.com"))
                .license("捷越联合")
                .licenseUrl("https://www.jieyuechina.com")
                .termsOfServiceUrl("https://www.jieyuechina.com")
                .build();
    }
}
