package com.alvaro.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import com.alvaro.springboot.di.app.springbootdi.models.Product;

public class ProductRepository {

    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(
            new Product(1L,"Memoria corsair 32",300L),
            new Product(2L, "Cpu Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mini 60%", 180L),
            new Product(4L, "Mothervoard Gigabyte", 490L)
        );
    }

    public List<Product> findAll(){

        return data;
    }
    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

    }

    
}
