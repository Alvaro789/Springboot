package com.alvaro.springboot.di.app.springbootdi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.springboot.di.app.springbootdi.models.Product;
import com.alvaro.springboot.di.app.springbootdi.services.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController {

    // Se instancia un servicio de productos
    private ProductService service = new ProductService();

    // Método para obtener una lista de productos
    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    // Método para obtener un producto por su ID
    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return service.findById(id);
    }
}
