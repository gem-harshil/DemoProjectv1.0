package com.example.project1gem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/swagger-ui.html");
        // This method can be blocked by the browser!
//        registry.addRedirectViewController("/", "redirect:/swagger-ui.html");
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

}