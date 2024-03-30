package com.addiis.core.gestionlogistica.controllers;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ResponseConsultaMaterialDTO;
import com.addiis.core.gestionlogistica.services.DistribucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distribucion")
public class DistribucionController {

    @Autowired
    DistribucionService distribucionService;
    @CrossOrigin(origins = "*")
    @GetMapping("/consultar")
    public ResponseEntity<ResponseConsultaMaterialDTO> consultaMaterial(
            @RequestParam(name = "sku") String sku
    ){
        try {
            return ResponseEntity.ok(distribucionService.consultaDistribucion(sku));
        }catch (Exception ex){
            AddiisLogger.error("Excepción no controlada", this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());

        }
        return null;
    }
}
