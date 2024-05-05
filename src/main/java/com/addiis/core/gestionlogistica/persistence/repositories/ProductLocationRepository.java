package com.addiis.core.gestionlogistica.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addiis.core.gestionlogistica.persistence.entities.Product;
import com.addiis.core.gestionlogistica.persistence.entities.ProductLocation;

@Repository
public interface ProductLocationRepository extends JpaRepository<ProductLocation, Long>{
    Optional<ProductLocation> findByProduct(Product product);
}
