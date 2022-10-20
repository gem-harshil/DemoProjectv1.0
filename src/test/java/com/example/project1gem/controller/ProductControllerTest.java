package com.example.project1gem.controller;

import com.example.project1gem.model.Product;
import com.example.project1gem.services.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CategoryControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.harshil.project1gem")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    /**
     * This is used initalize the product1 and product2 value before performing any Test.
     */
    @BeforeEach
    void intit() {
        product1 = new Product(1, 10000, "Realme 6", "6GB Ram", new Date(), new Date(), true, false);
        product2 = new Product(2, 20000, "Realme 7", "8GB Ram", new Date(), new Date(), true, false);
    }

    /**
     * Save Product Test
     *
     * @throws Exception
     */
    @Test
    void saveProductTest() throws Exception {

        when(productService.saveProduct(any(Product.class))).thenReturn(product1);

        this.mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productName", is(product1.getProductName())))
                .andExpect(jsonPath("$.productDesc", is(product1.getProductDesc())));

    }

    /**
     * Get All Product Test.
     *
     * @throws Exception
     */
    @Test
    void getAllProductTest() throws Exception {

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        when(productService.getAllProducts()).thenReturn(list);

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    /**
     * Get Product By Id Test.
     *
     * @throws Exception
     */
    @Test
    void getProductByIdTest() throws Exception {

        when(productService.getProductById(anyInt())).thenReturn(product1);

        this.mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(product1.getProductName())))
                .andExpect(jsonPath("$.productDesc", is(product1.getProductDesc())));
    }

    /**
     * Update Product Test.
     *
     * @throws Exception
     */
    @Test
    void updateProductTest() throws Exception {

        when(productService.updateProduct(anyInt(), any(Product.class))).thenReturn(product1);
        this.mockMvc.perform(put("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(product1.getProductName())))
                .andExpect(jsonPath("$.productDesc", is(product1.getProductDesc())));
    }

    /**
     * Delete Product Test.
     *
     * @throws Exception
     */
    @Test
    void deleteProductTest() throws Exception {

        doNothing().when(productService).deleteProduct(anyInt());

        this.mockMvc.perform(delete("/products")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

}
