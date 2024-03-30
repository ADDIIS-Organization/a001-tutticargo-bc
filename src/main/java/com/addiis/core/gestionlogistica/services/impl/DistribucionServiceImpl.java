package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ResponseConsultaMaterialDTO;
import com.addiis.core.gestionlogistica.services.DistribucionService;
import org.springframework.stereotype.Service;

@Service
public class DistribucionServiceImpl implements DistribucionService {
    @Override
    public ResponseConsultaMaterialDTO consultaDistribucion(String sku) {
        AddiisLogger.info("Ejecutando el controlador de consulta de distribución");
        return new ResponseConsultaMaterialDTO(sku, "Pruebas",
                1.0f,"s", "dd", "dd");
    }
}
