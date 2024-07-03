package com.addiis.core.gestionlogistica.controllers;


import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ReceptionScannedProductRequest;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.repositories.ReceptionScannedProductRepository;
import com.addiis.core.gestionlogistica.response.ApiResponse;
import com.addiis.core.gestionlogistica.services.ReceptionScannedProductService;
import com.addiis.core.gestionlogistica.services.WarehouseLocationService;
import com.addiis.core.gestionlogistica.services.ProductService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import com.addiis.core.gestionlogistica.utils.enums.SortType;

@RestController
@RequestMapping("/reception-scanned-products")
public class ReceptionScannedProductController {

    private Map<String, Object> error = new HashMap<>();

    private final ReceptionScannedProductService lastProductScannedService;
    private final WarehouseLocationService warehouseLocationService;

    @Autowired
    public ReceptionScannedProductController(ReceptionScannedProductService lastProductScannedService,
            WarehouseLocationService warehouseLocationService) {
        this.lastProductScannedService = lastProductScannedService;
        this.warehouseLocationService = warehouseLocationService;
    }

    @GetMapping
    public Object getAll(Pageable pageable) {
        return ApiResponse.ok(lastProductScannedService.getAll(pageable));
    }

    @PostMapping
    public Object create(@RequestBody ReceptionScannedProductRequest request) {

        Long warehouseLocationId = (Long) request.getWarehouseLocationId();
        LocalDate expirationDate = LocalDate.parse((String) request.getExpirationDate());
        LocalDate manufactureDate = LocalDate.parse((String) request.getManufactureDate());

        if (warehouseLocationId == null) {
            // return ApiResponse.noStringError("Product ID is required");
            error.put("warehouse", "warehouse ID is required");
        }

        if (expirationDate == null) {
            String errorPhrase = "Expiration date is required";
            error.put("expirationDate", errorPhrase);
        }

        if (manufactureDate == null) {
            String errorPhrase = "Manufacture date is required";
            error.put("manufactureDate", errorPhrase);
        }

        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "ReceptionScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        Optional<WarehouseLocation> productLocation = warehouseLocationService.findById(warehouseLocationId);
        if (!productLocation.isPresent()) {
            error.put("product", "Product not found");
        }

        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "ReceptionScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        ReceptionScannedProduct ReceptionScannedProduct = new ReceptionScannedProduct();
        ReceptionScannedProduct.setExpirationDate(expirationDate);
        ReceptionScannedProduct.setManufactureDate(manufactureDate);
        ReceptionScannedProduct.setUsefulLife(request.getUsefulLife());
        ReceptionScannedProduct.setReceptionPercentage(request.getReceptionPercentage());
        ReceptionScannedProduct.setWarehouseLocation(productLocation.get());
        

        try {
            ReceptionScannedProduct savedProduct = lastProductScannedService.save(ReceptionScannedProduct);
            return ApiResponse.ok(savedProduct);
        } catch (Exception ex) {
            AddiisLogger.error("Error saving last scanned product", "ReceptionScannedProductController", "create",
                    ex.getMessage());
            return ApiResponse.errorWithDetails("Error saving last scanned product", error);
        }
    }
}
