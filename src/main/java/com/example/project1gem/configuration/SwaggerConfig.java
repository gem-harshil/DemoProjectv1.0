package com.example.project1gem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger custom documentation.
     *
     * @return Docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.project1gem"))
                .build()
                .apiInfo(apiDetails());
    }

    /**
     * This method provide custom details for API Documentation
     * related to project.
     *
     * @return ApiInfo
     */
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Category & Product API Documentation",
                "Created Category & Product Api for Project",
                "1.0",
                "https://www.geminisolutions.com/",
                new springfox.documentation.service.Contact("Harshil Gupta",
                        "https://github.com/harshilgupta-gem",
                        "harshil.gupta@geminisolutions.com"),
                "API License",
                "https://github.com/harshilgupta-gem",
                Collections.emptyList()
        );
    }
}
