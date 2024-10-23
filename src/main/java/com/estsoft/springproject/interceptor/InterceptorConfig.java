package com.estsoft.springproject.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {  // 인터셉터 구현하는 방식이니 한번 기억해두면 좋음

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
    registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/books");
    }
}
