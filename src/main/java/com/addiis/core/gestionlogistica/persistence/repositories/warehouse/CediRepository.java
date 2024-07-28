package com.addiis.core.gestionlogistica.persistence.repositories.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Cedi;


public interface CediRepository extends JpaRepository<Cedi, Long> {
  
}
