package com.alvaro.trabajo.springboot.incidencias.springbootincidencias.models;

import java.util.Date;
import java.util.List;

public class Incidencia {
    private Long id;
    private String cliente;
    private String interlocutor;
    private String descripcionProblema;
    private Date fechaCreacion;
    private String estadoActual;
    private String comentarios;
    private String prioridad;
    private String asignadoA;
    private Date fechaResolucion;
    private List<String> adjuntos;
    public Incidencia() {
    }
    public Incidencia(Long id, String cliente, String interlocutor, String descripcionProblema, Date fechaCreacion,
            String estadoActual, String comentarios, String prioridad, String asignadoA, Date fechaResolucion,
            List<String> adjuntos) {
        this.id = id;
        this.cliente = cliente;
        this.interlocutor = interlocutor;
        this.descripcionProblema = descripcionProblema;
        this.fechaCreacion = fechaCreacion;
        this.estadoActual = estadoActual;
        this.comentarios = comentarios;
        this.prioridad = prioridad;
        this.asignadoA = asignadoA;
        this.fechaResolucion = fechaResolucion;
        this.adjuntos = adjuntos;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getInterlocutor() {
        return interlocutor;
    }
    public void setInterlocutor(String interlocutor) {
        this.interlocutor = interlocutor;
    }
    public String getDescripcionProblema() {
        return descripcionProblema;
    }
    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public String getAsignadoA() {
        return asignadoA;
    }
    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }
    public Date getFechaResolucion() {
        return fechaResolucion;
    }
    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    public List<String> getAdjuntos() {
        return adjuntos;
    }
    public void setAdjuntos(List<String> adjuntos) {
        this.adjuntos = adjuntos;
    }
    @Override
    public String toString() {
        return "Incidencia [id=" + id + ", cliente=" + cliente + ", interlocutor=" + interlocutor
                + ", descripcionProblema=" + descripcionProblema + ", fechaCreacion=" + fechaCreacion
                + ", estadoActual=" + estadoActual + ", comentarios=" + comentarios + ", prioridad=" + prioridad
                + ", asignadoA=" + asignadoA + ", fechaResolucion=" + fechaResolucion + ", adjuntos=" + adjuntos + "]";
    }

    
}

