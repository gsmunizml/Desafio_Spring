package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.ProductFilterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProduct service;

    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getList(ProductFilterParam filter){
        return new ResponseEntity<>(service.getList(filter), HttpStatus.OK);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> addProductList(@RequestBody List<Product> products) {
        return new ResponseEntity<>(service.addProductList(products), HttpStatus.CREATED);
    }
}
