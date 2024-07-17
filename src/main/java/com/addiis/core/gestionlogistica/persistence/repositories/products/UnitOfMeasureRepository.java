package com.addiis.core.gestionlogistica.persistence.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.product.UnitOfMeasure;

public interface UnitOfMeasureRepository  extends JpaRepository<UnitOfMeasure, Long> {
  
}
