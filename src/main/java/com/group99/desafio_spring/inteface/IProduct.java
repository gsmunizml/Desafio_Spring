package com.group99.desafio_spring.inteface;
import com.group99.desafio_spring.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProduct {
    List<Product> getAll();

    List<Product> getFiltered(Optional<String> category, Optional<Boolean> freeShipping, Optional<Integer> order, Optional<String> prestige);
}
