package com.example.project1gem.services;

import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
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
     * This method is used to Get All the Product from database
     *
     * @return List<Product>
     * @throws NoResourceFoundException No data found in database
     */
    @Override
    public List<Product> getAllProducts() throws NoResourceFoundException {
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()) {
            log.error("Nothing found in Product ");
            throw new NoResourceFoundException("No Data is present in data base");
        }
        log.info("Product List Found");
        return list;
    }

    /**
     * This method is used to Get specific the Product from database based on id.
     *
     * @param id productId
     * @return Product
     * @throws IdNotFoundException No productId found
     */
    @Override
    public Product getProductById(int id) throws IdNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty() || productOptional.get().isDeleted()) {
            log.error("Invalid product id");
            throw new IdNotFoundException(id + " id not found");
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
        Product product1 = productRepository.save(product);
        log.info("save product");
        return product1;
    }

    /**
     * This method is used to update the Product.
     *
     * @param product product body
     * @param id      product id
     * @return Product
     */
    @Override
    public Product updateProduct(int id, Product product) throws IdNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Invalid product Id");
            throw new IdNotFoundException(id + " Id not found please enter valid product id");
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
     * @throws IdNotFoundException throws exception when productId not found
     */
    @Override
    public void deleteProduct(int id) throws IdNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.error(id + " id not found");
            throw new IdNotFoundException(id + " product id not found");
        }
        optionalProduct.get().setActive(false);
        optionalProduct.get().setDeleted(true);
        productRepository.save(optionalProduct.get());
    }

}
