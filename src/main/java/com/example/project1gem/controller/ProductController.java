package com.example.project1gem.controller;


import com.example.project1gem.model.Product;
import com.example.project1gem.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get All Products.
     *
     * @return Response Entity of for product
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get All Products",
            notes = "This Http request is used to retrieve all Products",
            response = Product.class)
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Getting All Product Data");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    /**
     * Get request to get product acc. to specific productId.
     *
     * @param id productId
     * @return ResponseEntity <Product>
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get Product By Id",
            notes = "Get All product information for the given id",
            response = Product.class)
    public ResponseEntity<Product> getProductById(@ApiParam("Id value required to get the specific object from product object from database") @PathVariable int id)  {
        log.debug("Getting Product Data by Id");
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    /**
     * Post requests to add new product object to database.
     *
     * @param product product Body
     * @return ResponseEntity<Product>
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "To Save Product",
            notes = "This Http request is used to save a new Product Object",
            response = Product.class)
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        log.debug("New Product Created Successfully");
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    /**
     * Put request to update existing product based on given productId.
     *
     * @param product product body
     * @param id      productId
     * @return ResponseEntity  <Product>
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "To Update Product",
            notes = "This Http request is used to update product on basis of id value",
            response = Product.class)
    public ResponseEntity<Product> updateProduct(@ApiParam("Id value required to update the specific object from product object from database") @Valid @RequestBody Product product, @PathVariable int id) {
        log.debug("Product Updated Successfully");
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    /**
     * Delete request ro delete product from product database.
     *
     * @param id productId
     * @return ResponseEntity <HttpStatus>
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "To Delete Product",
            notes = "This Http request is used to delete specific record from Product",
            response = Product.class)
    public ResponseEntity<HttpStatus> deleteProduct(@ApiParam("Id value required to delete the specific object from product object from database") @RequestParam int id) {
        productService.deleteProduct(id);
        log.debug("Content Deleted Successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
