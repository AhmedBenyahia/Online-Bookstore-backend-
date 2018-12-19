package com.insat.config;

import com.insat.Interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
        .addPathPatterns("/api/**",
                "/order/**",
                "/person/delete/**",
                "/person/id/**",
                "/person/info/**")
        ;
    }
}
