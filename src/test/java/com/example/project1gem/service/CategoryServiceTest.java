package com.example.project1gem.service;

import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Category;
import com.example.project1gem.repository.CategoryRepository;
import com.example.project1gem.services.CategoryServiceImpl;
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
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    /**
     * Get All Category Test.
     *
     * @throws NoResourceFoundException No data present
     */
    @Test
    public void testGetAllCategory() throws NoResourceFoundException {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null));
        categoryList.add(new Category(2, "Mobile", "This category is of Mobile", new Date(), new Date(), true, false, null));

        when(categoryRepository.findAll()).thenReturn(categoryList);
        assertNotNull(categoryList);
        assertEquals(2, categoryService.getAllCategory().size());
    }

    /**
     * Get All Category Negative Testcase.
     */
    @Test
    public void testGetAllCategoryException() {

        List<Category> myCategory = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(myCategory);

        assertThatThrownBy(() -> categoryService.getAllCategory())
                .isInstanceOf(NoResourceFoundException.class);
    }

    /**
     * Get Category Id test.
     *
     * @throws IdNotFoundException Id not found
     */
    @Test
    public void testGetCategoryById() throws IdNotFoundException {
        Category category = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        assertEquals(1, categoryService.getCategoryById(1).getCategoryId());
        assertEquals("Laptop", this.categoryService.getCategoryById(1).getCategoryName());
        assertEquals("This category is of Laptop", this.categoryService.getCategoryById(1).getCategoryDescription());
    }

    /**
     * Get Category By Id negative Testcase.
     */
    @Test
    public void testGetCategoryByIdException() {
        when(categoryRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.getCategoryById(1))
                .isInstanceOf(IdNotFoundException.class);
    }

    /**
     * Save Category Test.
     */
    @Test
    public void testSaveCategory() {
        Category category = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category newCategory = categoryService.saveCategory(category);

        assertNotNull(newCategory);
        assertEquals(category, newCategory);
    }

    /**
     * \
     * Update Category Test.
     *
     * @throws IdNotFoundException Id not found
     */
    @Test
    public void testUpdateCategory() throws IdNotFoundException {
        Category category = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        category.setCategoryName("Mobile");
        Category existingCategory = categoryService.updateCategory(category.getCategoryId(), category);
        assertNotNull(existingCategory);
        assertEquals("Mobile", category.getCategoryName());

    }

    /**
     * UPdate Category Negative Testcase.
     */
    @Test
    public void testUpdateCategoryException() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        Category category = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);
        assertThatThrownBy(() -> categoryService.updateCategory(1, category))
                .isInstanceOf(IdNotFoundException.class);
    }

    /**
     * Delete Category Test.
     *
     * @throws IdNotFoundException id no found
     */
    @Test
    public void testDeleteCategory() throws IdNotFoundException {
        Category category = new Category(1, "Laptop", "This category is of Laptop", new Date(), new Date(), true, false, null);

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        categoryService.deleteCategory(1);

        assertThatThrownBy(() -> categoryService.getCategoryById(1))
                .isInstanceOf(IdNotFoundException.class);

    }

}
