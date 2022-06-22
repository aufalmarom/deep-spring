package com.domain.explore.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.domain.explore.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    // Derived Query Creation from Method Name ~ ORM
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
