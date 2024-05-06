package com.addiis.core.gestionlogistica.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.addiis.core.gestionlogistica.annotation.ApiResponseWrapper;
import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.services.ProductLocationService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Controller for handling product location requests
 * 
 * @Author: Nicolas Picon Jaimes
 * @version: 1.0.0
 * @since: 2024-05-05
 */
@RestController
@RequestMapping("/product-locations")
public class ProductLocationController {

    private final ProductLocationService productLocationService;

    public ProductLocationController(ProductLocationService productLocationService) {
        this.productLocationService = productLocationService;
    }

    @GetMapping("/{sku}")
    @ApiResponseWrapper
    public ResponseEntity<?> getProductLocationBySku(@PathVariable String sku) {
        AddiisLogger.info("Requesting product location for sku: " + sku);
        if (sku == null || sku.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid SKU provided"); // Just return the message
        }
        try {
            ProductLocationResponseDTO productLocation = productLocationService.getByCode(sku);
            return ResponseEntity.ok(productLocation);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
