package com.example.project1gem.services;

import com.example.project1gem.model.Product;
import com.example.project1gem.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method is used to Get All the Product from database.
     *
     * @return List<Product>
     */
    @Override
    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No Data is present in data base");
        }
        log.debug("Product List Found");
        return list;
    }

    /**
     * This method is used to Get specific the Product from database based on id.
     *
     * @param id productId
     * @return Product
     */
    @Override
    public Product getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty() || productOptional.get().isDeleted()) {
            throw new IllegalArgumentException(id + " id not found");
        }
        return productOptional.get();
    }

    /**
     * This method is used to Save Category to the database.
     *
     * @param product product body
     * @return Product
     */
    @Override
    public Product saveProduct(Product product) {
        log.debug("save product");
        return productRepository.save(product);
    }

    /**
     * This method is used to update the Product.
     *
     * @param product product body
     * @param id      product id
     * @return Product
     */
    @Override
    public Product updateProduct(int id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException(id + " Id not found please enter valid product id");
        }
        Product existingProduct = productOptional.get();
        existingProduct.setProductDesc(product.getProductDesc() != null ? product.getProductDesc() : existingProduct.getProductDesc());
        existingProduct.setProductName((product.getProductName() != null ? product.getProductName() : existingProduct.getProductName()));
        existingProduct.setActive(product.isActive());
        existingProduct.setDeleted(product.isDeleted());
        return productRepository.save(existingProduct);
    }

    /**
     * This method is used to delete the specific product from the database based on product id.
     *
     * @param id product id
     */
    @Override
    public void deleteProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException(id + " product id not found");
        }
        optionalProduct.get().setActive(false);
        optionalProduct.get().setDeleted(true);
        productRepository.save(optionalProduct.get());
    }

}
