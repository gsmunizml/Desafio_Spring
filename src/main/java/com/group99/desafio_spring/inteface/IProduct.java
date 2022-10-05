package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.dto.ProductDTO;

import java.util.List;

public interface IProduct {
    List<ProductDTO> getAll();
}
