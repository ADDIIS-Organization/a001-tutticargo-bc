package com.addiis.core.gestionlogistica.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addiis.core.gestionlogistica.persistence.entities.LastScannedProduct;

/**
 * Repository that allows to save the last scanned product.
 * 
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */
@Repository
public interface LastScannedProductRepository extends JpaRepository<LastScannedProduct, Long> {

    
}
