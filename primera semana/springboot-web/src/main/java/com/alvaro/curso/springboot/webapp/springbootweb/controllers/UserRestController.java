package com.alvaro.curso.springboot.webapp.springbootweb.controllers;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.curso.springboot.webapp.springbootweb.models.User;
import com.alvaro.curso.springboot.webapp.springbootweb.models.dto.Userdto;



@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping(path="/details")
    
    public Userdto details(){

        User user = new User("alvaro", "prado");

        Userdto userdto = new Userdto();
        userdto.setUser(user);
        userdto.setTitle("Hola Mundo Spring boot");
        return userdto;
    }
   
    
    @GetMapping(path = "/list")
    public List<User> list() {
        User user = new User("Alvaro", "prado");
        User user2 = new User("Pepe", "Tibet");
        User user3 = new User("Jon", "Doe");

        List<User> users = Arrays.asList(user,user2,user3);
       // List<User> users = new ArrayList<>();
       // users.add(user);
       // users.add(user2);
       // users.add(user3);

        return users;
    }

    @GetMapping(path="/details-map")
    public Map<String, Object> detailsMap(){
        User user = new User("alvaro", "prado");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola Mundo Spring boot");
        body.put("user", user);
        return body;
    }
}
