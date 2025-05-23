package com.addiis.core.gestionlogistica.persistence.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByEan(BigInteger ean);
}
