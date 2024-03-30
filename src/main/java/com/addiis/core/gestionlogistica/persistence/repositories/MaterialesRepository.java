package com.addiis.core.gestionlogistica.persistence.repositories;

import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialesRepository extends JpaRepository<MaterialesEntity, Long> {
    Optional<MaterialesEntity> findByCodigoMaterial(String codigoMaterial);
}
