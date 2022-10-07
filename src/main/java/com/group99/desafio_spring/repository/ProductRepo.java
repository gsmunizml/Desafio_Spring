package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.group99.desafio_spring.exceptions.IdAlreadyRegisteredException;
import com.group99.desafio_spring.exceptions.ReadFileException;
import com.group99.desafio_spring.exceptions.WriteFileException;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.util.TicketIdGenerator;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepo {
    private final String productsPathFile = "src/main/resources/products.json";
    private TicketIdGenerator ticketIdGenerator;
    ObjectMapper mapper = new ObjectMapper();

    public ProductRepo() {
        ticketIdGenerator = TicketIdGenerator.getInstance();
    }

    /**
     * Lista todos os itens do estoque
     * @return uma List com todos os itens
     */
    public List<Product> getAll(){
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(productsPathFile), Product[].class));
        } catch (Exception ex) {
            throw new ReadFileException(ex.getMessage());
        }

        return products;
    }

    /**
     * Lista o produto com ‘id’ especificado.
     * @param id id do produto a ser buscado.
     * @return Lista com o item especificado pelo ‘id’.
     */
    public Optional<Product> getProductById(int id) {
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(productsPathFile), Product[].class));
        } catch (Exception ex) {

        }

        for(Product p: products) {
            if(Integer.compare(p.getProductId(), id) == 0)
                return Optional.of(p);
        }

        return Optional.empty();
    }

    /**
     * Adiciona um novo produto ao estoque.
     * @param productList Lista de produtos a serem adicionados.
     */
    public void addProductList(List<Product> productList) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> savedProducts = new ArrayList<>(this.getAll());

        productList.forEach((product) -> {
            savedProducts.forEach((savedProduct) -> {
                if (savedProduct.getProductId() == product.getProductId()) {
                    throw new IdAlreadyRegisteredException("O id: " + product.getProductId() + " já está cadastrado.");
                }
            });
        });

        savedProducts.addAll(productList);

        try {
            writer.writeValue(new File(productsPathFile), savedProducts);
        } catch (Exception ex) {
            throw new WriteFileException(ex.getMessage());
        }
    }

    /**
     * Atualiza o estoque de produtos.
     * @param products Lista atualizada de produtos do estoque.
     */
    public void updateInventory(List<Product> products){
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File(productsPathFile), products);
        } catch (Exception ex) {
            throw new WriteFileException(ex.getMessage());
        }
    }
}
