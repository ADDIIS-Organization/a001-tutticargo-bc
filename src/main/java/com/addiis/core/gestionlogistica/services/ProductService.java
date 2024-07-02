package com.addiis.core.gestionlogistica.services;

import java.util.List;
import java.util.Optional;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

public interface ProductService {
    void save(Product product);

    List<Product> findAll();

    Optional<Product> findByEan(Integer ean);

    Optional<Product> findById(Long id);
}
