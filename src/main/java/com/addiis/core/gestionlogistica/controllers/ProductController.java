package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.addiis.core.gestionlogistica.annotation.ApiResponseWrapper;
import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{ean}")
    @ApiResponseWrapper
    public ResponseEntity<?> getproductBySku(@PathVariable Integer ean) {
        AddiisLogger.info("Requesting product location for ean: " + ean);
        if (ean == null || ean.toString().length() <= 0) {
            return ResponseEntity.badRequest().body("Invalid ean provided"); // Just return the message
        }
        try {
            ProductLocationResponseDTO productLocation = productService.getByEan(ean);
            return ResponseEntity.ok(productLocation);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
