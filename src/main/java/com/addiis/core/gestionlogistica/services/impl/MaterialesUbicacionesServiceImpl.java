package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesUbicacionEntity;
import com.addiis.core.gestionlogistica.persistence.entities.UbicacionesEntity;
import com.addiis.core.gestionlogistica.persistence.repositories.MaterialesUbicacionRepository;
import com.addiis.core.gestionlogistica.services.MaterialesUbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialesUbicacionesServiceImpl implements MaterialesUbicacionService {
    @Autowired
    private MaterialesUbicacionRepository materialesUbicacionRepository;
    @Override
    public void save(MaterialesUbicacionEntity materialesUbicacionEntity) {
        materialesUbicacionRepository.save(materialesUbicacionEntity);
        AddiisLogger.info("Almacenando MaterialUbicacion " +
                materialesUbicacionEntity.getMaterial()
                .getDescripcionMaterial() +" - "
                +materialesUbicacionEntity.getUbicacion().getDescripcionUbicacion());
    }

    @Override
    public List<MaterialesUbicacionEntity> findAll() {
        return materialesUbicacionRepository.findAll();
    }

    @Override
    public Optional<MaterialesUbicacionEntity> findByMaterial(MaterialesEntity materialesEntity) {
        return materialesUbicacionRepository.findByMaterial(materialesEntity);
    }

    @Override
    public Optional<MaterialesUbicacionEntity> findByUbicacion(UbicacionesEntity ubicacionesEntity) {
        return materialesUbicacionRepository.findByUbicacion(ubicacionesEntity);
    }
}
