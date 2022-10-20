package com.example.project1gem.service;

import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Product;
import com.example.project1gem.repository.ProductRepository;
import com.example.project1gem.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    /**
     * Get All Product Test.
     *
     * @throws NoResourceFoundException No data found
     */
    @Test
    public void testGetAllProducts() throws NoResourceFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false));
        productList.add(new Product(2, 20000, "Realme 7", "8GB Ram", new Date(), new Date(), true, false));

        when(productRepository.findAll()).thenReturn(productList);
        assertNotNull(productList);
        assertEquals(2, productService.getAllProducts().size());
    }

    /**
     * Get All Product Negative Testcase.
     */
    @Test
    public void testGetAllProductException() {
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        assertThatThrownBy(() -> productService.getAllProducts())
                .isInstanceOf(NoResourceFoundException.class);
    }

    /**
     * Get Product By Id Test.
     *
     * @throws IdNotFoundException id not found
     */
    @Test
    public void testGetProductById() throws IdNotFoundException {

        Product product = new Product(1, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        assertEquals(1, productService.getProductById(1).getProductId());
        assertEquals("Realme 6", productService.getProductById(1).getProductName());
        assertEquals("6GB Ram", productService.getProductById(1).getProductDesc());
    }

    /**
     * Get Product By Id negative Testcase.
     */
    @Test
    public void testGetProductByIdException() {
        when(productRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProductById(1))
                .isInstanceOf(IdNotFoundException.class);
    }

    /**
     * Save Product Test.
     */
    @Test
    public void testSaveProduct() {
        Product product = new Product(1, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product newProduct = productService.saveProduct(product);
        assertNotNull(newProduct);
        assertEquals(product, newProduct);
    }

    /**
     * Update Product Test.
     *
     * @throws IdNotFoundException id not found
     */
    @Test
    public void testUpdateProduct() throws IdNotFoundException {
        Product product = new Product(2, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        product.setProductName("Taxi");
        Product exisitingProduct = productService.updateProduct(product.getProductId(), product);

        assertNotNull(exisitingProduct);
        assertEquals("Taxi", product.getProductName());

    }

    /**
     * Update product negative testcase.
     */
    @Test
    public void testUpdateProductException() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        Product product = new Product(2, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);

        assertThatThrownBy(() -> productService.updateProduct(1, product))
                .isInstanceOf(IdNotFoundException.class);
    }

    /**
     * Delete Product Test
     *
     * @throws IdNotFoundException
     * @throws NoResourceFoundException
     */
    @Test
    public void testDeleteProduct() throws IdNotFoundException, NoResourceFoundException {
        Product product = new Product(2, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);
        List<Product> productList = List.of(product);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        productService.deleteProduct(2);
        when(productRepository.findAll()).thenReturn(productList);

        assertEquals(1, productService.getAllProducts().size());
        assertThatThrownBy(() -> productService.getProductById(2))
                .isInstanceOf(IdNotFoundException.class);
    }
}
