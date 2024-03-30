package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ResponseConsultaMaterialDTO;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesEntity;
import com.addiis.core.gestionlogistica.persistence.entities.MaterialesUbicacionEntity;
import com.addiis.core.gestionlogistica.services.DistribucionService;
import com.addiis.core.gestionlogistica.services.MaterialesService;
import com.addiis.core.gestionlogistica.services.MaterialesUbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistribucionServiceImpl implements DistribucionService {

    private String nombreClase = this.getClass().getName();
    private String nombreMetodo = null;

    @Autowired
    private MaterialesService materialesService;

    @Autowired
    private MaterialesUbicacionService materialesUbicacionService;

    @Override
    public ResponseConsultaMaterialDTO consultaDistribucion(String sku) {
        AddiisLogger.info("Ejecutando el controlador de consulta de distribución SKU="+sku);
        nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        if (sku == null){
            AddiisLogger.error("Consulta sin parámetro GET SKU", nombreClase,
                    nombreMetodo, null);
            return null;
        }

        Optional<MaterialesEntity> material = materialesService.findByCodigoMaterial(sku);

        if (material.isEmpty()){
            AddiisLogger.warn("Consulta por material sin resultados");
            return null;
        }

        Optional<MaterialesUbicacionEntity> materialesUbicacion
                = materialesUbicacionService.findByMaterial(material.get());

        if (materialesUbicacion.isEmpty()){
            AddiisLogger.warn("Consulta por material-ubicación sin resultados");
            return null;
        }

        MaterialesUbicacionEntity resultados = materialesUbicacion.get();

        return new ResponseConsultaMaterialDTO(sku, resultados.getMaterial().getDescripcionMaterial(),
                resultados.getCantidad(), resultados.getUbicacion().getCodigoUbicacion(),
                resultados.getUbicacion().getSector(), resultados.getUbicacion().getEspacio());
    }
}
