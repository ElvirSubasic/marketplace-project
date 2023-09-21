package com.marketplace.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marketplace.backend.exception.ResourceNotFoundException;
import com.marketplace.backend.helper.ProductHelper;
import com.marketplace.backend.model.Product;
import com.marketplace.backend.repository.ProductRepository;
import com.marketplace.backend.util.SortingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getFilteredProducts(
            Long categoryId,
            String field,
            String direction,
            Integer offset,
            Integer pageSize,
            Double latitude,
            Double longitude,
            Integer nearMeDistance
    ) {
        SortingUtil sortingUtil = new SortingUtil();
        Sort sort = sortingUtil.generateSortCalss(field, direction);
        PageRequest pagination = sortingUtil.generatePaginationClass(offset, pageSize, sort);
        return new ProductHelper().retrieveProducts(
                productRepository,
                pagination,
                field,
                direction,
                sort,
                categoryId,
                latitude,
                longitude,
                nearMeDistance
        );
    }

    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        return ResponseEntity.ok(product);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<Product> updateProductById(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setLatitude(productDetails.getLatitude());
        product.setLongitude(productDetails.getLongitude());
        product.setImage(productDetails.getImage());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());

        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity<Product> incrementProductViewById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        if (product.getViews() == null) {
            product.setViews(1);
        } else {
            product.setViews(product.getViews() + 1);
        }

        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity<Map<String, Boolean>> deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
