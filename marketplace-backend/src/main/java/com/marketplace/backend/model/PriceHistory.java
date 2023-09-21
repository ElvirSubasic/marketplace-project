package com.marketplace.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "price_histories")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_history_id")
    private Long priceHistoryId;
    @Column(name = "price")
    private Float price;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @ManyToOne
    private Product product;

    public PriceHistory() {
    }

    public PriceHistory(Long priceHistoryId, Float price, Date timestamp, Product product) {
        this.priceHistoryId = priceHistoryId;
        this.price = price;
        this.timestamp = timestamp;
        this.product = product;
    }

    public Long getPriceHistoryId() {
        return priceHistoryId;
    }

    public void setPriceHistoryId(Long priceHistoryId) {
        this.priceHistoryId = priceHistoryId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonBackReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
