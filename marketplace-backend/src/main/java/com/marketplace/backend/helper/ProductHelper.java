package com.marketplace.backend.helper;

import com.marketplace.backend.model.Product;
import com.marketplace.backend.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ProductHelper {
    public List<Product> retrieveProducts(ProductRepository productRepository, PageRequest pagination, String field, String direction, Sort sort, Long categoryId, Double latitude, Double longitude, Integer nearMeDistance) {
        boolean noInfoProvided = pagination == null && sort == null && categoryId == null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlySortProvided = pagination == null && sort != null && categoryId == null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlyPaginationProvided = pagination != null && sort == null && categoryId == null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlyCategoryIdProvided = pagination == null && sort == null && categoryId != null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlyCategoryIdAndSortProvided = pagination == null && sort != null && categoryId != null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlyCategoryIdAndPaginationProvided = pagination != null && sort == null && categoryId != null && latitude == null && longitude == null && nearMeDistance == null;
        boolean onlyLocationsProvided = pagination == null && sort == null && categoryId == null && latitude != null && longitude != null && nearMeDistance != null;
        boolean onlyLocationsAndSortProvided = pagination == null && sort != null && categoryId == null && latitude != null && longitude != null && nearMeDistance != null;
        boolean onlyLocationsAndPaginationProvided = pagination != null && sort == null && categoryId == null && latitude != null && longitude != null && nearMeDistance != null;
        boolean onlyLocationAndCategoryIdProvided = pagination == null && sort == null && categoryId != null && latitude != null && longitude != null && nearMeDistance != null;
        boolean onlyLocationAndCategoryIdAndSortProvided = pagination == null && sort != null && categoryId != null && latitude != null && longitude != null && nearMeDistance != null;


        if (noInfoProvided) {
            return productRepository.findAll();
        }

        if (onlySortProvided) {
            return productRepository.findAll(sort);
        }

        if (onlyPaginationProvided) {
            return productRepository.findAll(pagination).getContent();
        }

        if (onlyCategoryIdProvided) {
            return productRepository.findProductsByCategoryId(categoryId);
        }

        if (onlyCategoryIdAndSortProvided) {
            return productRepository.findProductsByCategoryId(categoryId, field, direction);
        }

        if (onlyCategoryIdAndPaginationProvided) {
            return productRepository.findProductsByCategoryId(categoryId, pagination).getContent();
        }

        if (onlyLocationsProvided) {
            return productRepository.findProductsByNearMeDistance(nearMeDistance, latitude, longitude);
        }

        if (onlyLocationsAndSortProvided) {
            return productRepository.findProductsByNearMeDistance(nearMeDistance, latitude, longitude, field, direction);
        }

        if (onlyLocationsAndPaginationProvided) {
            return productRepository.findProductsByNearMeDistance(nearMeDistance, latitude, longitude, pagination).getContent();
        }

        if (onlyLocationAndCategoryIdProvided) {
            return productRepository.findProductsByNearMeDistanceAndCategoryId(nearMeDistance, latitude, longitude, categoryId);
        }

        if (onlyLocationAndCategoryIdAndSortProvided) {
            return productRepository.findProductsByNearMeDistanceAndCategoryId(nearMeDistance, latitude, longitude, categoryId, field, direction);
        }

        return productRepository.findProductsByNearMeDistanceAndCategoryId(nearMeDistance, latitude, longitude, categoryId, pagination).getContent();
    }
}
