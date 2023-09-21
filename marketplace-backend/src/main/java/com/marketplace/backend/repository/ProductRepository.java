package com.marketplace.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.marketplace.backend.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE category_id = ?1",
            nativeQuery = true)
    List<Product> findProductsByCategoryId(Long categoryId);

    @Query(value = "SELECT * FROM products WHERE category_id = ?1",
            nativeQuery = true)
    Page<Product> findProductsByCategoryId(Long categoryId, PageRequest pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId ORDER BY :field :direction",
            nativeQuery = true)
    List<Product> findProductsByCategoryId(
            @Param("categoryId") Long categoryId,
            @Param("field") String field,
            @Param("direction") String direction
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000",
            nativeQuery = true)
    List<Product> findProductsByNearMeDistance(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000",
            nativeQuery = true)
    Page<Product> findProductsByNearMeDistance(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            PageRequest pageable
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000  ORDER BY :field :direction",
            nativeQuery = true)
    List<Product> findProductsByNearMeDistance(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("field") String field,
            @Param("direction") String direction
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000 AND category_Id = :categoryId",
            nativeQuery = true)
    List<Product> findProductsByNearMeDistanceAndCategoryId(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("categoryId") Long categoryId
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000 AND category_Id = :categoryId",
            nativeQuery = true)
    Page<Product> findProductsByNearMeDistanceAndCategoryId(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("categoryId") Long categoryId,
            PageRequest pageable
    );

    @Query(value = "SELECT * FROM products WHERE ST_Distance_Sphere(Point(longitude, latitude),Point(:longitude, :latitude)) <= :distance * 1000 AND category_Id = :categoryId ORDER BY :field :direction",
            nativeQuery = true)
    List<Product> findProductsByNearMeDistanceAndCategoryId(
            @Param("distance") Integer distance,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("categoryId") Long categoryId,
            @Param("field") String field,
            @Param("direction") String direction
    );
}
