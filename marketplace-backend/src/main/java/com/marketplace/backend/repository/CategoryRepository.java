package com.marketplace.backend.repository;

import java.awt.print.Pageable;
import java.util.List;

import com.marketplace.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
