package com.alvaro.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.alvaro.springboot.di.app.springbootdi.models.Product;
import com.alvaro.springboot.di.app.springbootdi.repositories.ProductRepository;

public class ProductService {
    // Se instancia un repositorio de productos
    private ProductRepository repository = new ProductRepository();

    // Método para encontrar todos los productos
    public List<Product> findAll(){
        // Se obtienen todos los productos del repositorio, se les aplica una transformación
        // para calcular el precio con un impuesto del 25% y se devuelve la lista resultante
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * 1.25d;
            //Product newProd = new Product(p.getId(), p.getName(), priceTax.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    // Método para encontrar un producto por su ID
    public Product findById(Long id){
        // Se busca un producto en el repositorio por su ID y se devuelve
        return repository.findById(id);
    }
}
