package com.addiis.core.gestionlogistica.persistence.repositories.products;

import org.springframework.data.domain.Pageable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;

/**
 * Repository that allows to save the last scanned product.
 * 
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */
@Repository
public interface ReceptionScannedProductRepository extends JpaRepository<ReceptionScannedProduct, Long> {
    
}
