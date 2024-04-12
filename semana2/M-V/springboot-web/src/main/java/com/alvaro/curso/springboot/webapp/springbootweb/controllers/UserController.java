package com.alvaro.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;


import com.alvaro.curso.springboot.webapp.springbootweb.models.User;


@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model){

        User user = new User("alvaro", "prado");
        user.setEmail("alvaroppyt@gmail.com");
        model.addAttribute("title", "Hola Mundo Spring boot");
        model.addAttribute("user", user);
        return "details";
    }
    @GetMapping("/list")
    public String list(ModelMap model){
        model.addAttribute("title", "Listado de usuarios!");
        return "list";

    }
    @ModelAttribute("users")
    public List<User> userModel(){
        return Arrays.asList(new User("pepa","Gonzalez"),
        new User("lalo","perez", "lalo@gmail.com"),
        new User("carlos", "perez", "carlos@gmail.com"),
        new User ("dan","doe"));
        

    }
    
    
    
}
