package com.group99.desafio_spring.service;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> getAll() {
        return repo.getAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Product> getFiltered(Optional<String> category, Optional<Boolean> freeShipping, Optional<Integer> order, Optional<String> prestige){
        List<Product> products = repo.getAll(); // adicionar filtro

        if(!order.isEmpty()){
            switch (order.get().intValue()){
                case 0:
                    return orderByAlphabeticNormal(products);
                case 1:
                    return orderByAlphabeticReverse(products);
                case 2:
                    return orderByBiggestPrice(products);
            }
        }

        return products.stream().collect(Collectors.toList());
    }

    private List<Product> orderByAlphabeticNormal(List<Product> products){
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    private List<Product> orderByAlphabeticReverse(List<Product> products){
        return products.stream().sorted(Comparator.comparing(Product::getName).reversed()).collect(Collectors.toList());
    }

    private List<Product> orderByBiggestPrice(List<Product> products) {
        return products.stream()
                .sorted((p1, p2) -> p2.getPrice().intValue() - p1.getPrice().intValue())
                .collect(Collectors.toList());
    }
}
