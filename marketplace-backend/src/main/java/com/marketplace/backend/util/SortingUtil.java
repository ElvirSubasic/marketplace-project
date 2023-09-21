package com.marketplace.backend.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

public class SortingUtil {
    public Sort generateSortCalss(String field, String direction) {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("ASC", Sort.Direction.ASC);
        sortMap.put("DESC", Sort.Direction.DESC);

        return field != null
                ? direction != null && sortMap.containsKey(direction)
                ? Sort.by(sortMap.get(direction), field)
                : Sort.by(field)
                : null;
    }

    public PageRequest generatePaginationClass(Integer offset, Integer pageSize, Sort sort) {
        return offset != null && pageSize != null
                ? sort != null
                ? PageRequest.of(offset, pageSize).withSort(sort)
                : PageRequest.of(offset, pageSize)
                : null;
    }
}
