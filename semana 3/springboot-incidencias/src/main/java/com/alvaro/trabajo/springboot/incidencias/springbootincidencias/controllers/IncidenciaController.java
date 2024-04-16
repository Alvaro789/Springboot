package com.alvaro.trabajo.springboot.incidencias.springbootincidencias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.alvaro.trabajo.springboot.incidencias.springbootincidencias.models.Incidencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IncidenciaController {
    // Lista para almacenar las incidencias
    private List<Incidencia> incidencias = new ArrayList<>();
    // Variable para generar IDs únicos
    private Long proximoId = 1L;
    

    public IncidenciaController() {
        cargaDeDatos(); 
    }

    private void cargaDeDatos() {
        incidencias.add(crearIncidencia(
            1L, 
            "pepe", 
            "alvaro", 
            "problema aire acondicionado", 
            "En proceso", 
            "No funciona", 
            "Alta", 
            "alvaro", 
            new Date(), 
            new ArrayList<>(List.of("Archivo1.jpg", "Archivo2.pdf"))
        ));
    
        incidencias.add(crearIncidencia(
            2L, 
            "jose", 
            "eduardo", 
            "No me funciona el coche",
            "Pendiente", 
            "Se ha roto el motor", 
            "Alta", 
            "miguel", 
            new Date(), 
            new ArrayList<>() 
        ));
    }
    
    // Método privado para crear una nueva incidencia
    private Incidencia crearIncidencia(Long id, String cliente, String interlocutor, String descripcionProblema, String estadoActual, String comentarios, String prioridad, String asignadoA, Date fechaCreacion, List<String> adjuntos) {
        Incidencia incidencia = new Incidencia();
        incidencia.setId(proximoId++);
        incidencia.setCliente(cliente);
        incidencia.setInterlocutor(interlocutor);
        incidencia.setDescripcionProblema(descripcionProblema);
        incidencia.setEstadoActual(estadoActual);
        incidencia.setComentarios(comentarios);
        incidencia.setPrioridad(prioridad);
        incidencia.setAsignadoA(asignadoA);
        incidencia.setFechaCreacion(fechaCreacion);
        incidencia.setFechaResolucion(null);
        incidencia.setAdjuntos(adjuntos);
        return incidencia;
    }
    

   

    // Método para mostrar la lista de incidencias
    @GetMapping("/verIncidencias")
    public String verIncidencias(Model model) {
        // Se agrega la lista de incidencias al modelo para que la vista la pueda mostrar
        model.addAttribute("incidencias", incidencias);
        // Se devuelve el nombre de la vista de la lista de incidencias
        return "listaIncidencias";
    }


    
    // Método para mostrar el formulario de creación de incidencias
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Se agrega un objeto Incidencia vacío al modelo para que el formulario lo pueda usar
        model.addAttribute("incidencia", new Incidencia());
        // Se devuelve el nombre de la vista del formulario
        return "formulario";
    }

    // Método para procesar la creación de una nueva incidencia
    @PostMapping("/crearIncidencia")
    public String crearIncidencia(@ModelAttribute Incidencia incidencia) {
        // Se asigna un ID único a la incidencia y se incrementa el valor de proximoId
        incidencia.setId(proximoId++);
        // Se establece la fecha de creación como la fecha y hora actual
        incidencia.setFechaCreacion(new Date());
        // Se agrega la incidencia a la lista de incidencias
        incidencias.add(incidencia);
        // Se redirige a la página que muestra la lista de incidencias
        return "redirect:/verIncidencias";
    }





    
}
