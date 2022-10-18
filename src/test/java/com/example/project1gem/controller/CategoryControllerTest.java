package com.example.project1gem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.project1gem.model.Category;
import com.example.project1gem.services.CategoryServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CategoryControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.harshil.project1gem")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryServiceImpl categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    void intit() {
        category1 = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);
        category2 = new Category(2, "Mobile", "This category is of Mobile", new Date(), new Date(), true, false, null);
    }

    @Test
    void saveCategoryTest() throws Exception {

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category1);

        this.mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryName", is(category1.getCategoryName())))
                .andExpect(jsonPath("$.categoryDescription", is(category1.getCategoryDescription())));

    }

    @Test
    void getAllCategoryTest() throws Exception {

        List<Category> list = new ArrayList<>();
        list.add(category1);
        list.add(category2);

        when(categoryService.getAllCategory()).thenReturn(list);

        this.mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getCategoryByIdTest() throws Exception {

        when(categoryService.getCategoryById(anyInt())).thenReturn(category1);

        this.mockMvc.perform(get("/category/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is(category1.getCategoryName())))
                .andExpect(jsonPath("$.categoryDescription", is(category1.getCategoryDescription())));
    }

    @Test
    void updateCategoryTest() throws Exception {

        when(categoryService.updateCategory(anyInt(), any(Category.class))).thenReturn(category1);
        this.mockMvc.perform(put("/category/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is(category1.getCategoryName())))
                .andExpect(jsonPath("$.categoryDescription", is(category1.getCategoryDescription())));

    }

    @Test
    void deleteCategoryTest() throws Exception {

        doNothing().when(categoryService).deleteCategory(anyInt());

        this.mockMvc.perform(delete("/category")
                        .param("id", "1"))
                .andExpect(status().isOk());

    }


}
