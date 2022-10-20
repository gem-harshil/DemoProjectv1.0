package com.example.project1gem.services;


import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory() throws NoResourceFoundException;

    Category getCategoryById(int id) throws IdNotFoundException;

    Category saveCategory(Category category);

    Category updateCategory(int id, Category category) throws IdNotFoundException;

    void deleteCategory(int id) throws IdNotFoundException;
}
