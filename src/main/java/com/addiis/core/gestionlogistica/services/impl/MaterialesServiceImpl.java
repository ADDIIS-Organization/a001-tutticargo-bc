package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import com.addiis.core.gestionlogistica.persistence.repositories.MaterialesRepository;
import com.addiis.core.gestionlogistica.services.MaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialesServiceImpl implements MaterialesService {
    @Autowired
    private MaterialesRepository materialesRepository;
    @Override
    public void save(MaterialesEntity materialesEntity) {
        materialesRepository.save(materialesEntity);
        AddiisLogger.info("Almacenando Material "+materialesEntity.getDescripcionMaterial());
    }

    @Override
    public List<MaterialesEntity> findAll() {
        return materialesRepository.findAll();
    }

    @Override
    public Optional<MaterialesEntity> findByCodigoMaterial(String codigoMaterial) {
        return materialesRepository.findByCodigoMaterial(codigoMaterial);
    }
}
