package com.marketplace.backend.controller;

import java.util.List;
import java.util.Map;

import com.marketplace.backend.model.PriceHistory;
import com.marketplace.backend.service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PriceHistoryController {
    @Autowired
    private PriceHistoryService service;

    @GetMapping("/priceHistories")
    public List<PriceHistory> getAllPriceHistories() {
        return service.getAllPriceHistories();
    }

    @GetMapping("/priceHistories/sort-pagination")
    public List<PriceHistory> getAllPriceHistoriesWithSortAndPagination(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        return service.getAllPriceHistoriesWithSortAndPagination(field, direction, offset, pageSize);
    }

    @GetMapping("/priceHistory/{id}")
    public ResponseEntity<PriceHistory> getPriceHistoryById(@PathVariable Long id) {
        return service.getPriceHistoryById(id);
    }

    @PostMapping("/priceHistory")
    public PriceHistory createPriceHistory(@RequestBody PriceHistory priceHistory) {
        return service.createPriceHistory(priceHistory);
    }

    @PutMapping("/priceHistory/{id}")
    public ResponseEntity<PriceHistory> updateProductById(@PathVariable Long id, @RequestBody PriceHistory priceHistoryDetails) {
        return service.updateProductById(id, priceHistoryDetails);
    }

    @DeleteMapping("/priceHistory/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProductById(@PathVariable Long id) {
        return service.deleteProductById(id);
    }
}
