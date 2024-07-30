package com.addiis.core.gestionlogistica.persistence.repositories.dispatch;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
  
}
