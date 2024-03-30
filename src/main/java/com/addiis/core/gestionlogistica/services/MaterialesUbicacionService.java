package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesUbicacionEntity;
import com.addiis.core.gestionlogistica.persistence.entities.UbicacionesEntity;

import java.util.List;
import java.util.Optional;

public interface MaterialesUbicacionService {
    void save(MaterialesUbicacionEntity materialesUbicacionEntity);
    List<MaterialesUbicacionEntity> findAll();

    Optional<MaterialesUbicacionEntity> findByMaterial(MaterialesEntity materialesEntity);

    Optional<MaterialesUbicacionEntity> findByUbicacion(UbicacionesEntity ubicacionesEntity);

}
