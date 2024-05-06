package com.addiis.core.gestionlogistica.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.Product;
import com.addiis.core.gestionlogistica.persistence.entities.ProductLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.ProductLocationRepository;
import com.addiis.core.gestionlogistica.services.ProductLocationService;
import com.addiis.core.gestionlogistica.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductLocationServiceImpl implements ProductLocationService {

    private final ProductService productService;
    private final ProductLocationRepository productLocationService;

    @Autowired
    public ProductLocationServiceImpl(ProductService productService, ProductLocationRepository productLocationService) {
        this.productService = productService;
        this.productLocationService = productLocationService;
    }

    public ProductLocationResponseDTO getByCode(String sku) {
        Optional<Product> product = productService.findByCode(sku);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product with SKU " + sku + " not found.");
        }
        AddiisLogger.info("Product found: " + product.get().getDescription());
        Optional<ProductLocation> productLocation = productLocationService.findByProduct(product.get());

        if (productLocation.isEmpty()) {
            throw new EntityNotFoundException("Location for product with SKU " + sku + " not found.");
        }

        return new ProductLocationResponseDTO(
                sku,
                product.get().getDescription(),
                productLocation.get().getWarehouseLocation().getCode(),
                productLocation.get().getWarehouseLocation().getSector(),
                productLocation.get().getWarehouseLocation().getSpace());
    }

}
