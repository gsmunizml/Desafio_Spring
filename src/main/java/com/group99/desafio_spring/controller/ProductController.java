package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProduct service;

    @GetMapping("/articles")
    public ResponseEntity<List<ProductDTO>> getList(@RequestParam Optional<String> category,
                                                    @RequestParam Optional<Boolean> freeShipping,
                                                    @RequestParam Optional<Integer> order,
                                                    @RequestParam Optional<String> prestige){

        if(order.isEmpty() && category.isEmpty() && freeShipping.isEmpty() && prestige.isEmpty())
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

        return new ResponseEntity<>(service.getFiltered(category, freeShipping, order, prestige), HttpStatus.OK);
    }
}
