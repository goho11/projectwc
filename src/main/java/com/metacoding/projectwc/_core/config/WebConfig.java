package com.metacoding.projectwc._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/img/**") // html에서 경로를 적으면
                .addResourceLocations("file:" + "./images/") // 웹서버의 /images/ 폴더 경로를 찾음
                .setCachePeriod(60 * 60); // 초 단위 => 한시간
    }
}