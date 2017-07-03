package com.nicefish.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 使用注解的方式来扫描API
 * <p/>
 * <p> @author sunye
 * <p/>
 * <p> @date 2017年6月26日 下午1:18:48
 * <p> @version 0.0.1
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Nicefish API")
                .description("Nicefish bootstrap rest api")
                .version("v1.0")
                .contact("317484255@qq.com,kimmking@163.com")
                .termsOfServiceUrl("https://git.oschina.net/nicefish/nicefish-backend")
                .build();
    }


    /*@Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("nicefish")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(metadata());
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfiguration.DEFAULT;
    }*/

}