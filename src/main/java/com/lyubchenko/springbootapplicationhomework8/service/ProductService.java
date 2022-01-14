package com.lyubchenko.springbootapplicationhomework8.service;

import com.lyubchenko.springbootapplicationhomework8.domain.Product;
import com.lyubchenko.springbootapplicationhomework8.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements Crud<Product>{


    @Autowired
    private ProductRepositories productRepositories;


    @Override
    public void create(Product entity) {
        productRepositories.save(entity);
    }

    @Override
    public List<Product> read() {
        return productRepositories.findAll();
    }

    @Override
    public void update(Product entity) {
        productRepositories.save(entity);
    }

    @Override
    public void deleteEntity(Product entity) {
        productRepositories.delete(entity);
    }

    @Override
    public Product getEntityById(UUID id) {
        Optional<Product> byId = productRepositories.findById(id);
        return byId.orElseGet(byId::get);
    }
}
