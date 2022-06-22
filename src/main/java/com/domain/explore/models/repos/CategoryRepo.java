package com.domain.explore.models.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.domain.explore.models.entities.Category;

// CRUD
// PagingASP : CRUD + Paging Sorting + saveAll bulk data
// JPA : CRUD + Paging Sorting + Save and Flush
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

}
