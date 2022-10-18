package com.example.project1gem.services;


import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws NoResourceFoundException;

    Product getProductById(int id) throws IdNotFoundException;

    Product saveProduct(Product product);

    Product updateProduct(int id, Product product) throws IdNotFoundException;

    void deleteProduct(int id) throws IdNotFoundException;

}
