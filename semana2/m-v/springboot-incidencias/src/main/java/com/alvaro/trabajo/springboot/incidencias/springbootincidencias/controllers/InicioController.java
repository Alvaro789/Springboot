package com.alvaro.trabajo.springboot.incidencias.springbootincidencias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String mostrarPaginaInicio(Model model) {
        // Aquí podrías agregar cualquier lógica necesaria antes de mostrar la página de inicio
        return "inicio";
    }
}
