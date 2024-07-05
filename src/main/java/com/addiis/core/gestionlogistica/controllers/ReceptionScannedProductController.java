package com.addiis.core.gestionlogistica.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ReceptionScannedProductRequest;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.response.ApiResponse;
import com.addiis.core.gestionlogistica.services.ReceptionScannedProductService;
import com.addiis.core.gestionlogistica.services.WarehouseLocationService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reception-scanned-products")
public class ReceptionScannedProductController {

    private Map<String, Object> error = new HashMap<>();

    private final ReceptionScannedProductService receptionScannedProductService;
    private final WarehouseLocationService warehouseLocationService;

    @Autowired
    public ReceptionScannedProductController(
            ReceptionScannedProductService receptionScannedProductService,
            WarehouseLocationService warehouseLocationService) {
        this.receptionScannedProductService = receptionScannedProductService;
        this.warehouseLocationService = warehouseLocationService;
    }

    @GetMapping
    public Object getAll(Pageable pageable) {
        return ResponseEntity.ok(receptionScannedProductService.getAll(pageable));
    }

    @PostMapping
    public Object create(@RequestBody ReceptionScannedProductRequest request) {

        Long warehouseLocationId = request.getWarehouseLocationId();
        LocalDate expirationDate = null;
        LocalDate manufactureDate = null;

        try {
            expirationDate = LocalDate.parse(request.getExpirationDate());
        } catch (DateTimeParseException ex) {
            error.put("expirationDate", "Invalid expiration date format");
        }

        try {
            manufactureDate = LocalDate.parse(request.getManufactureDate());
        } catch (DateTimeParseException ex) {
            error.put("manufactureDate", "Invalid manufacture date format");
        }

        if (warehouseLocationId == null) {
            error.put("warehouse", "Warehouse ID is required");
        }

        if (expirationDate == null) {
            error.put("expirationDate", "Expiration date is required or invalid");
        }

        if (manufactureDate == null) {
            error.put("manufactureDate", "Manufacture date is required or invalid");
        }

        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "ReceptionScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        Optional<WarehouseLocation> productLocation = warehouseLocationService.findById(warehouseLocationId);
        if (!productLocation.isPresent()) {
            error.put("warehouseLocation", "Warehouse location not found");
        }

        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "ReceptionScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        ReceptionScannedProduct receptionScannedProduct = new ReceptionScannedProduct();
        receptionScannedProduct.setExpirationDate(expirationDate);
        receptionScannedProduct.setManufactureDate(manufactureDate);
        receptionScannedProduct.setUsefulLife(request.getUsefulLife());
        receptionScannedProduct.setReceptionPercentage(request.getReceptionPercentage());
        receptionScannedProduct.setWarehouseLocation(productLocation.get());

        try {
            ReceptionScannedProduct savedProduct = receptionScannedProductService.save(receptionScannedProduct);
            return ApiResponse.ok(savedProduct);
        } catch (Exception ex) {
            AddiisLogger.error("Error saving last scanned product", "ReceptionScannedProductController", "create",
                    ex.getMessage());
            return ApiResponse.errorWithDetails("Error saving last scanned product", error);
        }
    }
}