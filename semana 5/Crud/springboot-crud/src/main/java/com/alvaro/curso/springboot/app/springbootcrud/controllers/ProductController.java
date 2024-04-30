package com.alvaro.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
// Importaciones necesarias
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.alvaro.curso.springboot.app.springbootcrud.ProductValidation;
import com.alvaro.curso.springboot.app.springbootcrud.entities.Product;
import com.alvaro.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.Valid;

// Controlador REST para productos
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Inyección del servicio de productos
    @Autowired
    private ProductService service;

    //@Autowired
    //private ProductValidation validation;

    // Endpoint para obtener todos los productos
    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }
    
    // Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> productOptional =  service.findById(id);
        // Verifica si el producto existe
        if(productOptional.isPresent()){
            // Si existe, devuelve el producto con un estado OK
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        // Si no existe, devuelve un estado de not found
        return ResponseEntity.notFound().build();
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
        //validation.validate(product, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        // Guarda el nuevo producto y devuelve un estado de created
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }
    


    // Endpoint para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){
        //validation.validate(product, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Product> productOptional = service.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        
        // Elimina el producto y devuelve un estado OK si se elimina con éxito
        Optional<Product> productOptional =  service.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        // Si el producto no se encuentra, devuelve un estado de not found
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}

