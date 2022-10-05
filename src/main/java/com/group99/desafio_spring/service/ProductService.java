package com.group99.desafio_spring.service;

import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> getAll() {
        return repo.getAll();
    }
}
