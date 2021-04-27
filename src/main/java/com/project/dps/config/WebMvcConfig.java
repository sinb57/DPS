package com.project.dps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/static/public/", "classpath:/META-INF/resources/public/");
        registry.addResourceHandler("/custom/**")
                .addResourceLocations("classpath:/static/custom/", "classpath:/META-INF/resources/custom/");
    }
}
