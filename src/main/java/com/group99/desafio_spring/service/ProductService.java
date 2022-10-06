package com.group99.desafio_spring.service;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.ProductFilterParam;
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

    /**
     * Método responsável por retornar uma coleção de Produtos
     * @return List<Product>
     */
    @Override
    public List<Product> getList(ProductFilterParam filter){
        List<Product> products = repo.getAll();
        if (!filter.hasFilterParam() && !filter.hasOrderParam())
            return products;

        return filterProducts(products, filter);
    }

    private List<Product> filterProducts(List<Product> products, ProductFilterParam filter){
        if(filter.getCategory() != null)
            products = this.filterProductByCategory(filter.getCategory(), products);

        if (filter.getCategory() != null && filter.getFreeShipping() != null)
            products = orderByCategoryShipping(products, filter.getCategory(), filter.getFreeShipping().booleanValue());

        if (filter.getFreeShipping() != null && filter.getPrestige() != null)
            products = orderByShippingPrestige(products, filter.getFreeShipping().booleanValue(), filter.getPrestige());

        if (filter.getOrder() != null)
            return sortProducts(products, filter.getOrder().intValue());

        return products;
    }

    private List<Product> sortProducts(List<Product> products, int order){
            switch (order){
                case 0:
                    return orderByAlphabeticNormal(products);
                case 1:
                    return orderByAlphabeticReverse(products);
                case 2:
                    return orderByBiggestPrice(products);
                case 3:
                    return orderByLowestPrice(products);
            }

        return products;
    }

    /**
     * Método responsável por adicionar uma lista de produtos
     * A lista de produtos retornados estão no formato da classe ProductDTO => {"id","name","quantity"}
     * @param products - Lista de produtos
     * @return List<ProductDTO>
     */
    @Override
    public List<ProductDTO> addProductList(List<Product> products) {
        repo.addProductList(products);
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    /**
     * Método para ordenar por ordem alfabética crescente
     * @param products - Lista de produtos
     * @return List<Product>
     */
    private List<Product> orderByAlphabeticNormal(List<Product> products){
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    /**
     * Método responsável por ordenar por ordem alfabética decrescente
     * @param products - Lista de produtos
     * @return List<Product>
     */
    private List<Product> orderByAlphabeticReverse(List<Product> products){
        return products.stream().sorted(Comparator.comparing(Product::getName).reversed()).collect(Collectors.toList());
    }

    /**
     * Método para ordenar a lista de produtos pelo maior preço
     * @param products - Lista de produtos
     * @return List<Product>
     */
    private List<Product> orderByBiggestPrice(List<Product> products) {
        return products.stream()
                .sorted((p1, p2) -> p2.getPrice().intValue() - p1.getPrice().intValue())
                .collect(Collectors.toList());
    }

    /**
     * Método responsável por ordenar uma lista de produtos pelo menor preço
     * @param products - Lista de produtos
     * @return List<Product>
     */
    private List<Product> orderByLowestPrice(List<Product> products) {
        return products.stream()
                .sorted((p1, p2) -> p1.getPrice().intValue() - p2.getPrice().intValue())
                .collect(Collectors.toList());
    }

    /**
     * Método responsável por filtrar de acordo com categoria e frete
     * @param products - Lista de produtos
     * @param category - categoria do produto
     * @param shipping - Boolean frete gratis
     * @return List<Product>
     */
    private List<Product> orderByCategoryShipping(List<Product> products, String category, boolean shipping) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category) && shipping == p.isFreeShipping())
                .collect(Collectors.toList());
    }

    /**
     * Método responsável por filtrar por frete e avaliação
     * @param products - Lista de produtos
     * @param shipping - Boolean frete gratis
     * @param prestige - Avaliação
     * @return List<Product>
     */
    private List<Product> orderByShippingPrestige(List<Product> products, boolean shipping, String prestige) {
        return products.stream()
                .filter(p -> shipping == p.isFreeShipping() && p.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

    /**
     * Método responsável por filtrar conforme categoria
     * @param category - Tipo da categoria
     * @param products - Lista de produtos
     * @return  List<Product>
     */
    private List<Product> filterProductByCategory(String category, List<Product> products) {
        return products.stream()
                .filter(p-> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
