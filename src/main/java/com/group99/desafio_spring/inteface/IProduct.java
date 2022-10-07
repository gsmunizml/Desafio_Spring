package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.ProductFilterParam;

import java.util.List;
import java.util.Optional;

public interface IProduct {
    List<Product> getList(ProductFilterParam filter);
    List<ProductDTO> addProductList(List<Product> products);
}
