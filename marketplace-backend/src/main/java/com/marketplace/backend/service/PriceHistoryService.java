package com.marketplace.backend.service;

import com.marketplace.backend.exception.ResourceNotFoundException;
import com.marketplace.backend.model.PriceHistory;
import com.marketplace.backend.repository.PriceHistoryRepository;
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
public class PriceHistoryService {
    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    public List<PriceHistory> getAllPriceHistories() {
        return priceHistoryRepository.findAll();
    }

    public List<PriceHistory> getAllPriceHistoriesWithSortAndPagination(
            String field,
            String direction,
            Integer offset,
            Integer pageSize
    ) {
        SortingUtil sortingUtil = new SortingUtil();
        Sort sort = sortingUtil.generateSortCalss(field, direction);
        PageRequest pagination = sortingUtil.generatePaginationClass(offset, pageSize, sort);
        if (pagination != null) {
            return priceHistoryRepository.findAll(pagination).getContent();
        }

        if (sort != null) {
            return priceHistoryRepository.findAll(sort);
        }

        return priceHistoryRepository.findAll();
    }


    public ResponseEntity<PriceHistory> getPriceHistoryById(Long id) {
        PriceHistory priceHistory = priceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price History not exist with id: " + id));

        return ResponseEntity.ok(priceHistory);
    }

    public PriceHistory createPriceHistory(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    public ResponseEntity<PriceHistory> updateProductById(Long id, PriceHistory priceHistoryDetails) {
        PriceHistory priceHistory = priceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price History not exist with id: " + id));
        priceHistory.setPrice(priceHistoryDetails.getPrice());
        priceHistory.setTimestamp(priceHistoryDetails.getTimestamp());

        return ResponseEntity.ok(priceHistoryRepository.save(priceHistory));
    }

    public ResponseEntity<Map<String, Boolean>> deleteProductById(Long id) {
        PriceHistory priceHistory = priceHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price History not exist with id: " + id));
        priceHistoryRepository.delete(priceHistory);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
