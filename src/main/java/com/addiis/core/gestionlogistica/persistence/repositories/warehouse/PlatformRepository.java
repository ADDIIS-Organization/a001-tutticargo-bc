package com.addiis.core.gestionlogistica.persistence.repositories.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Platform;

public interface PlatformRepository  extends JpaRepository<Platform, Long> {
  
}
