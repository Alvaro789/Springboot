package com.alvaro.curso.springboot.calendar.interceptor.springboothorario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("calendarInterceptor")
    private HandlerInterceptor calendar;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendar).addPathPatterns("/foo");
    }
    

}