package com.domain.explore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.explore.models.entities.Product;
import com.domain.explore.models.entities.Supplier;
import com.domain.explore.models.repos.ProductRepo;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID : " + productId + " not found");
        }
        product.getSupplier().add(supplier);
        save(product);
    }

    public Product findProductByName(String name) {
        return productRepo.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name) {
        return productRepo.findProductByNameLike("%" + name + "%");
    }

    public List<Product> findProductByCat(Long categoryId) {
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findProductBySupp(Long supplierId) {
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null) {
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }

}
