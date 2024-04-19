package com.alvaro.curso.springboot.app.interceptor.springbootinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Afecta a todas las rutas que vayan después del /app/, como bar, baz y foo.
        
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/app/**");
        
        // Afecta a las rutas /app/bar y /app/foo.
        
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo");
       
        // Este excluye la ruta /app/bar del interceptor.
        
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar");
    }
}
