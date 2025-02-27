package com.BootJourney2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")  // 웹에서 접근할 URL
                .addResourceLocations("file:/Users/donghwan/Documents/code/BootJourney2/src/main/resources/upload_files/");  // ✅ 실제 파일 저장 경로 (마지막 `/` 추가)
    }
}