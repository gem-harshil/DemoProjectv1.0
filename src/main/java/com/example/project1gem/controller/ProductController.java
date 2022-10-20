package com.example.project1gem.controller;


import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Product;
import com.example.project1gem.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get All Products.
     *
     * @return Response Entity of for product
     * @throws NoResourceFoundException No Data found
     */
    @GetMapping("/products")
    @ApiOperation(
            value = "Get All Products",
            notes = "This Http request is used to retrieve all Products",
            response = Product.class)
    public ResponseEntity<List<Product>> getAllProducts() throws NoResourceFoundException {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        log.info("Getting All Product Data");
        return responseEntity;
    }

    /**
     * Get request to get product acc. to specific productId.
     *
     * @param id productId
     * @return ResponseEntity <Product>
     * @throws IdNotFoundException product id not found
     */
    @GetMapping("/products/{id}")
    @ApiOperation(
            value = "Get Product By Id",
            notes = "Get All product information for the given id",
            response = Product.class)
    public ResponseEntity<Product> getProductById(@ApiParam("Id value required to get the specific object from product object from database") @PathVariable int id) throws IdNotFoundException {

        ResponseEntity<Product> data = new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        log.info("Getting Product Data by Id");
        return data;

    }

    /**
     * Post requests to add new product object to database.
     *
     * @param product product Body
     * @return ResponseEntity<Product>
     */
    @PostMapping("/products")
    @ApiOperation(
            value = "To Save Product",
            notes = "This Http request is used to save a new Product Object",
            response = Product.class)
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
        log.info("New Product Created Successfully");
        return productResponseEntity;
    }

    /**
     * Put request to update existing product based on given productId.
     *
     * @param product product body
     * @param id      productId
     * @return ResponseEntity  <Product>
     */
    @PutMapping("/products/{id}")
    @ApiOperation(
            value = "To Update Product",
            notes = "This Http request is used to update product on basis of id value",
            response = Product.class)
    public ResponseEntity<Product> updateProduct(@ApiParam("Id value required to update the specific object from product object from database") @Valid @RequestBody Product product, @PathVariable int id) throws IdNotFoundException {
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
        log.info("Product Updated Successfully");
        return responseEntity;
    }

    /**
     * Delete request ro delete product from product database.
     *
     * @param id productId
     * @return ResponseEntity <HttpStatus>
     */
    @DeleteMapping("/products")
    @ApiOperation(
            value = "To Delete Product",
            notes = "This Http request is used to delete specific record from Product",
            response = Product.class)
    public ResponseEntity<HttpStatus> deleteProduct(@ApiParam("Id value required to delete the specific object from product object from database") @RequestParam int id) throws IdNotFoundException {

        productService.deleteProduct(id);
        log.info("Content Deleted Successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
