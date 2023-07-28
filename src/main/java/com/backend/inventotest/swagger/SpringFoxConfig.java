package com.backend.inventotest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Component
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.inventotest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("Invento Test Backend API",
                "Invento Test API",
                "1.0",
                "Terms of service",
                new Contact("Invento", "www.test.co",
                        "testmail@gmail.com"),
                "License of API",
                "www.test.co/licenses",
                Collections.emptyList());
    }


}
