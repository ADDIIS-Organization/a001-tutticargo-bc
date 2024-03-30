package com.addiis.core.gestionlogistica.persistence.repositories;

import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesUbicacionEntity;
import com.addiis.core.gestionlogistica.persistence.entities.UbicacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialesUbicacionRepository extends JpaRepository<MaterialesUbicacionEntity, Long> {

    Optional<MaterialesUbicacionEntity> findByMaterial(MaterialesEntity materialesEntity);
    Optional<MaterialesUbicacionEntity> findByUbicacion(UbicacionesEntity ubicacionesEntity);

}
