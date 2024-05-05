package com.addiis.core.gestionlogistica.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.services.ProductLocationService;

@RestController
@RequestMapping("/product-locations")
public class ProductLocationController {

    private final ProductLocationService productLocationService;

    public ProductLocationController(ProductLocationService productLocationService) {
        this.productLocationService = productLocationService;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getProductLocationBySku(@PathVariable String sku) {
        AddiisLogger.info("Requesting product location for sku: " + sku);
        if (sku == null) {
            return ResponseEntity.badRequest().body("SKU cannot be null");
        }
        try {
            ProductLocationResponseDTO productLocation = productLocationService.getByCode(sku);
            AddiisLogger.info("Product location found: " + productLocation);
            if (productLocation == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(productLocation);
        } catch (Exception e) {
            AddiisLogger.error("Error finding product location for sku: " + sku, "ProductLocationController",
                    "getProductLocationBySku", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error finding product location for sku: " + sku);
        }
    }
}
