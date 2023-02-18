package com.example.project1gem.services;


import com.example.project1gem.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() ;

    Product getProductById(int id) ;

    Product saveProduct(Product product);

    Product updateProduct(int id, Product product) ;

    void deleteProduct(int id) ;

}
