package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;

import java.util.List;
import java.util.Optional;

public interface MaterialesService {
    void save(MaterialesEntity materialesEntity);

    List<MaterialesEntity> findAll();

    Optional<MaterialesEntity> findByCodigoMaterial(String codigoMaterial);
}
