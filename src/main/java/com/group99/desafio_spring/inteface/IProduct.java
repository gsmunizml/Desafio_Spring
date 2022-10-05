package com.group99.desafio_spring.inteface;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProduct {
    List<Product> getAll();
    List<ProductDTO> addProductList(List<Product> products);
    List<Product> getFiltered(Optional<String> category, Optional<Boolean> freeShipping, Optional<Integer> order, Optional<String> prestige);
}
