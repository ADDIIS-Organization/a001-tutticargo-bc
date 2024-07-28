package com.addiis.core.gestionlogistica.persistence.repositories.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
  
}
