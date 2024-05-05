package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);

    List<Product> findAll();

    Optional<Product> findByCode(String codigoMaterial);
}
