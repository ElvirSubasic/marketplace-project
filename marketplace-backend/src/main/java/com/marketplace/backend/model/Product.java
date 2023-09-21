package com.marketplace.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 32768, columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private Float price;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "views")
    private Integer views;
    @Column(name = "image", columnDefinition = "mediumblob")
    private String image;

    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistories;

    public Product() {
    }

    public Product(Long productId, String name, String description, Float price, Double latitude, Double longitude, Integer views, String image, List<PriceHistory> priceHistories, Category category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.views = views;
        this.image = image;
        this.priceHistories = priceHistories;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PriceHistory> getPriceHistories() {
        return priceHistories;
    }

    public void setPriceHistories(List<PriceHistory> priceHistories) {
        this.priceHistories = priceHistories;
    }
}
