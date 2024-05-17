package com.addiis.core.gestionlogistica.controllers;

import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.LastScannedProductRequest;
import com.addiis.core.gestionlogistica.persistence.entities.LastScannedProduct;
import com.addiis.core.gestionlogistica.persistence.entities.Product;
import com.addiis.core.gestionlogistica.persistence.entities.ProductLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.LastScannedProductRepository;
import com.addiis.core.gestionlogistica.response.ApiResponse;
import com.addiis.core.gestionlogistica.services.LastScannedProductService;
import com.addiis.core.gestionlogistica.services.ProductLocationService;
import com.addiis.core.gestionlogistica.services.ProductService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import com.addiis.core.gestionlogistica.utils.enums.SortType;

@RestController
@RequestMapping("/last-scanned-products")
public class LastScannedProductController {

    private Map<String, Object> error = new HashMap<>();

    private final LastScannedProductService lastProductScannedService;
    private final ProductLocationService productLocationService;

    @Autowired
    public LastScannedProductController(LastScannedProductService lastProductScannedService,
            ProductLocationService productLocationService) {
        this.lastProductScannedService = lastProductScannedService;
        this.productLocationService = productLocationService;
    }

    @GetMapping
    public Object getAll(Pageable pageable) {
        return ApiResponse.ok(lastProductScannedService.getAll(pageable));
    }

    @PostMapping
    public Object create(@RequestBody LastScannedProductRequest request) {

        Long productLocationId = (Long) request.getProductLocationId();
        LocalDate expirationDate = LocalDate.parse((String) request.getExpirationDate());

        if (productLocationId == null) {
            // return ApiResponse.noStringError("Product ID is required");
            error.put("product", "Product ID is required");
        }

        if (expirationDate == null) {
            String errorPhrase = "Expiration date is required";
            error.put("expirationDate", errorPhrase);
        }
        AddiisLogger.info(error.toString());
        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "LastScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        Optional<ProductLocation> productLocation = productLocationService.findById(productLocationId);
        if (!productLocation.isPresent()) {
            error.put("product", "Product not found");
        }

        if (!error.isEmpty()) {
            AddiisLogger.error("Validation error", "LastScannedProductController", "create", error.toString());
            return ApiResponse.errorWithDetails("Validation error", this.error);
        }

        LastScannedProduct lastScannedProduct = new LastScannedProduct();
        lastScannedProduct.setProductLocation(productLocation.get());
        lastScannedProduct.setManufactureDate(request.getManufactureDate());
        lastScannedProduct.setExpirationDate(expirationDate);
        lastScannedProduct.setCreatedBy("nicolas.picon");
        lastScannedProduct.setUpdatedBy("nicolas.picon");

        try {
            LastScannedProduct savedProduct = lastProductScannedService.save(lastScannedProduct);
            return ApiResponse.ok(savedProduct);
        } catch (Exception ex) {
            AddiisLogger.error("Error saving last scanned product", "LastScannedProductController", "create",
                    ex.getMessage());
            return ApiResponse.errorWithDetails("Error saving last scanned product", error);
        }
    }
}
