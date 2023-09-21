package com.marketplace.backend.controller;

import com.marketplace.backend.model.Category;
import com.marketplace.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/categories/sort-pagination")
    public List<Category> getAllCategoriesWithSortAndPagination(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {

        return service.getAllCategoriesWithSortAndPagination(field, direction, offset, pageSize);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping("/category")
    public Category createCategory(Category category) {
        return service.createCategory(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return service.updateCategoryById(id, categoryDetails);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategoryById(@PathVariable Long id) {
        return service.deleteCategoryById(id);
    }
}
