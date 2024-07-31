package com.addiis.core.gestionlogistica.persistence.repositories.vehicles;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;

public interface DriverRepository  extends JpaRepository<Driver, Long> {
  
}
