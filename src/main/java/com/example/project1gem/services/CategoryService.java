package com.example.project1gem.services;


import com.example.project1gem.model.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(int id);

    Category saveCategory(Category category);

    Category updateCategory(int id, Category category);

    void deleteCategory(int id);
}
