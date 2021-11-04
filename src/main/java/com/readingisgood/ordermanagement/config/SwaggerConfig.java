package com.readingisgood.ordermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ParameterBuilder paramBuilder = new ParameterBuilder();

        List<Parameter> params = new ArrayList<>();
        paramBuilder.name("Authorization").modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        params.add(paramBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(params)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.readingisgood.ordermanagement.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Reading Is Good APIs")
                .description("Order Management API")
                .version("1.0")
                .build();
    }
}
