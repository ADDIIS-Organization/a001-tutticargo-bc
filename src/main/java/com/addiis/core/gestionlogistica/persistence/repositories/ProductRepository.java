package com.addiis.core.gestionlogistica.persistence.repositories;

import com.addiis.core.gestionlogistica.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String codigoMaterial);
}
