package com.addiis.core.gestionlogistica.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.WarehouseLocation;

public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, Long>{
    
}
