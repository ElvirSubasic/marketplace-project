package com.marketplace.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.marketplace.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.marketplace.backend.model.Product;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/products/filter")
    public List<Product> getFilteredProducts(
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "latitude", required = false) Double latitude,
            @RequestParam(name = "longitude", required = false) Double longitude,
            @RequestParam(name = "nearMeDistance", required = false) Integer nearMeDistance
    ) {
        return service.getFilteredProducts(categoryId, field, direction, offset, pageSize, latitude, longitude, nearMeDistance);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product productDetails) {
        return service.updateProductById(id, productDetails);
    }

    @PutMapping("/product/view/{id}")
    public ResponseEntity<Product> incrementProductViewById(@PathVariable Long id) {
        return service.incrementProductViewById(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProductById(@PathVariable Long id) {
        return service.deleteProductById(id);
    }
}
