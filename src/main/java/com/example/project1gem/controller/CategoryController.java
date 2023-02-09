package com.example.project1gem.controller;

import com.example.project1gem.exception.IdNotFoundException;
import com.example.project1gem.exception.NoResourceFoundException;
import com.example.project1gem.model.Category;
import com.example.project1gem.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.validation.Valid;

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


@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Get All Category.
     *
     * @return reponse entity
     * @throws NoResourceFoundException no data found
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get All Category",
            notes = "This Http request is used to retrieve all Categories",
            response = Category.class)
    public ResponseEntity<List<Category>> getAllCategory() throws NoResourceFoundException {
        log.debug("Response Status for getAllCategory() : ",HttpStatus.OK);
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    /**
     * Get Request to Get all the category acc.to specific category id.
     *
     * @param id categoryId
     * @return ResponseEntity<Category>
     * @throws NoResourceFoundException Generate exception on invalid categoryId
     */
    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get Category By Id",
            notes = "Get All category information for the given id",
            response = Category.class)
    public ResponseEntity<Category> getCategoryById(@ApiParam(value = "Id value for the category you need to retrieve", required = true) @PathVariable int id) throws IdNotFoundException {
        log.debug("Response Status for getCategoryById(() : ",HttpStatus.OK);
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    /**
     * Post requests to add category object in database.
     *
     * @param category category body to save
     * @return ResponseEntity<Category>
     */
    @PostMapping("/category")
    @ApiOperation(value = "To Save Category", notes = "This Http request is used to save a new Category Object", response = Category.class)
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
        ResponseEntity<Category> categoryResponseEntity = new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
        log.debug("Response Status for saveCategory() : ",HttpStatus.OK);
        return categoryResponseEntity;
    }

    /**
     * Put request to update category.
     *
     * @param category category body
     * @param id       categoryId
     * @return ResponseEntity<Category>
     */
    @PutMapping("/category/{id}")
    @ApiOperation(
            value = "To Update Category",
            notes = "This Http request is used to update category on basis of id value",
            response = Category.class)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @ApiParam(name = "Id Value required to update specific Category record", required = true) @PathVariable int id) throws IdNotFoundException {
        ResponseEntity<Category> categoryResponseEntity = new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
        log.debug("Response Status for updateCategory() : ",HttpStatus.OK);
        return categoryResponseEntity;
    }

    /**
     * Delete request to delete category from database.
     *
     * @param id categoryId
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/category")
    @ApiOperation(
            value = "To Delete Category",
            notes = "This Http request is used to delete specific record from Categories",
            response = Category.class)
    public ResponseEntity<HttpStatus> deleteCategory(@ApiParam("Id value required to delete the specific object from category object from database") @RequestParam int id) throws IdNotFoundException {
        categoryService.deleteCategory(id);
        log.debug("Response Status for updateCategory() : ",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
