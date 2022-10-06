package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProduct service;

    /**
     * Método responsável por retornar uma lista de produtos
     * A lista retornada pode variar conforme os filtros passados por parâmetro
     * @param category - Categoria( ex: "Esportes" )
     * @param freeShipping - Frete Gratis (Verdadeiro ou Falso)
     * @param order - Ordem alfabética( 1 - crescente, -1 decrescente)
     * @param prestige - Avaliação do produto
     * @return ResponseEntity(List<Product>, Status Code)
     */
    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getList(@RequestParam Optional<String> category,
                                                    @RequestParam Optional<Boolean> freeShipping,
                                                    @RequestParam Optional<Integer> order,
                                                    @RequestParam Optional<String> prestige){

        if(order.isEmpty() && category.isEmpty() && freeShipping.isEmpty() && prestige.isEmpty())
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

        return new ResponseEntity<>(service.getFiltered(category, freeShipping, order, prestige), HttpStatus.OK);
    }

    /**
     * Método responsável por criar uma lista de produtos
     * @param products - Produtos([{"productId","name","category","brand":,"price","quantity","freeShipping","prestige"}])
     * @return ResponseEntity(List<Product>, Status Code)
     */
    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> addProductList(@RequestBody List<Product> products) {
        return new ResponseEntity<>(service.addProductList(products), HttpStatus.CREATED);
    }
}
