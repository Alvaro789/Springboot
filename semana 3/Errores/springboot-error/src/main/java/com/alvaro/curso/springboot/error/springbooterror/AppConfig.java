package com.alvaro.curso.springboot.error.springbooterror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alvaro.curso.springboot.error.springbooterror.models.domain.User;

@Configuration
public class AppConfig {

    
    @Bean
    List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Pepe", "Gonzalez"));
        users.add(new User(2L, "Alvaro", "Prado"));
        users.add(new User(3L, "Luis", "Perez"));
        users.add(new User(4L, "Jose", "Ruiz"));
        users.add(new User(5L, "Ana", "Doe"));
        return users;
    }
    
}
