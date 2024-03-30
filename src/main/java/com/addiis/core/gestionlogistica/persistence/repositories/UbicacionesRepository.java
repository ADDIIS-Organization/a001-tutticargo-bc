package com.addiis.core.gestionlogistica.persistence.repositories;

import com.addiis.core.gestionlogistica.persistence.entities.UbicacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionesRepository extends JpaRepository<UbicacionesEntity, Long> {
    Optional<UbicacionesEntity> findByCodigoUbicacion(String codigoAplicacion);
}
