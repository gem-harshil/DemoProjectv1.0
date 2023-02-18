package com.example.project1gem.services;


import com.example.project1gem.model.Category;
import com.example.project1gem.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This method is used to Get All the Category from database.
     *
     * @return List<Category>
     */
    @Override
    public List<Category> getAllCategory() {
        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No Data is present in data base");
        }
        log.debug("Category List Found");
        return list;
    }

    /**
     * This method is used to Get specific the Category from database based on id.
     *
     * @param id categoryId
     * @return Category
     */
    @Override
    public Category getCategoryById(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty() || categoryOptional.get().isDeleted()) {
            throw new IllegalArgumentException(id + " id not found");
        }
        return categoryOptional.get();
    }

    /**
     * This method is used to Save Category to the database.
     *
     * @param category category Body
     * @return Category
     */
    @Override
    public Category saveCategory(Category category) {
        log.debug("save Category");
        return categoryRepository.save(category);
    }

    /**
     * This method is used to update the Category.
     *
     * @param category category body
     * @param id       category Id to update category
     * @return Category
     */
    @Override
    public Category updateCategory(int id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException(id + " Id not found please enter valid category id");
        }
        Category existingCategory = optionalCategory.get();
        existingCategory.setCategoryDescription(category.getCategoryDescription() != null ? category.getCategoryDescription() : existingCategory.getCategoryDescription());
        existingCategory.setCategoryName((category.getCategoryName() != null ? category.getCategoryName() : existingCategory.getCategoryName()));
        existingCategory.setActive(category.isActive());
        existingCategory.setDeleted(category.isDeleted());
        return categoryRepository.save(existingCategory);
    }

    /**
     * This method is used to delete the specific category
     * from the database based on category id.
     *
     * @param id categoryId
     */
    @Override
    public void deleteCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException(id + " category id not found");
        }
        optionalCategory.get().setActive(false);
        optionalCategory.get().setDeleted(true);
        categoryRepository.save(optionalCategory.get());
    }
}
