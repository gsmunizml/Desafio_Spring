package com.group99.desafio_spring.service;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        List<Product> products = repo.getAll();

        if(category.isPresent()){
            products = this.filterProductByCategory(category.get(),products);
        }

        if (category.isPresent() && freeShipping.isPresent()) {
           products = orderByCategoryShipping(products, category.get(), freeShipping.get());
        }

        if (freeShipping.isPresent() && prestige.isPresent()) {
           products = orderByShippingPrestige(products, freeShipping.get(), prestige.get());
        }

        if(!order.isEmpty()){
            switch (order.get().intValue()){
                case 0:
                    return orderByAlphabeticNormal(products);
                case 1:
                    return orderByAlphabeticReverse(products);
                case 2:
                    return orderByBiggestPrice(products);
                case 3:
                    return orderByLowestPrice(products);
            }
        }

        return products;
    }
    
    @Override
    public List<ProductDTO> addProductList(List<Product> products) {
        repo.addProductList(products);
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> addProductList(List<Product> products) {
        repo.addProductList(products);
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public PurchaseTicket purchaseRequest(List<PurchaseRequestItem> purchaseRequestItems){
        return  repo.purchaseRequest(purchaseRequestItems);
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

    private List<Product> orderByLowestPrice(List<Product> products) {
        return products.stream()
                .sorted((p1, p2) -> p1.getPrice().intValue() - p2.getPrice().intValue())
                .collect(Collectors.toList());
    }

    private List<Product> orderByCategoryShipping(List<Product> products, String category, boolean shipping) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category) && shipping == p.isFreeShipping())
                .collect(Collectors.toList());
    }

    private List<Product> orderByShippingPrestige(List<Product> products, boolean shipping, String prestige) {
        return products.stream()
                .filter(p -> shipping == p.isFreeShipping() && p.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

    private List<Product> filterProductByCategory(String category, List<Product> products) {
        return products.stream()
                .filter(p-> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
