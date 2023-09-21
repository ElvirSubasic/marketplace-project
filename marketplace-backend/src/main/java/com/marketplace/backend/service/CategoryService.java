package com.marketplace.backend.service;

import com.marketplace.backend.exception.ResourceNotFoundException;
import com.marketplace.backend.model.Category;
import com.marketplace.backend.repository.CategoryRepository;
import com.marketplace.backend.util.SortingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllCategoriesWithSortAndPagination(
            String field,
            String direction,
            Integer offset,
            Integer pageSize
    ) {
        SortingUtil sortingUtil = new SortingUtil();
        Sort sort = sortingUtil.generateSortCalss(field, direction);
        PageRequest pagination = sortingUtil.generatePaginationClass(offset, pageSize, sort);
        if (pagination != null) {
            return categoryRepository.findAll(pagination).getContent();
        }

        if (sort != null) {
            return categoryRepository.findAll(sort);
        }

        return categoryRepository.findAll();
    }


    public ResponseEntity<Category> getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));

        return ResponseEntity.ok(category);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public ResponseEntity<Category> updateCategoryById(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        category.setName(categoryDetails.getName());

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    public ResponseEntity<Map<String, Boolean>> deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        categoryRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

}
