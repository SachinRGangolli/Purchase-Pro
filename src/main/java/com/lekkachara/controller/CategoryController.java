package com.lekkachara.controller;

import com.lekkachara.entities.Brand;
import com.lekkachara.entities.Category;
import com.lekkachara.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        Optional<Category> categoryById = categoryService.getCategoryById(id);
        if (categoryById.isPresent()) {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category with Id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory (@PathVariable("id") Long id){
        Optional<Category> catOptional = categoryService.getCategoryById(id);
        if (catOptional.isPresent()) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Category with Id " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category with Id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
