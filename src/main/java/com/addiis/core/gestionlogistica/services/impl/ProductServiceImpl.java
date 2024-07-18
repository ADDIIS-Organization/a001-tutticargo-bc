package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationDTO;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.ProductRepository;
import com.addiis.core.gestionlogistica.services.ProductService;
import com.addiis.core.gestionlogistica.services.WarehouseLocationService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final WarehouseLocationService warehouseLocationService;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            WarehouseLocationService warehouseLocationService) {
        this.productRepository = productRepository;
        this.warehouseLocationService = warehouseLocationService;
    }

    @Override
    public void save(Product product) {
        Product productCreated = productRepository.save(product);
        AddiisLogger.info("Almacenando Material " + productCreated.getObservation());
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByEan(BigInteger ean) {
        return productRepository.findByEan(ean);
    }

    @Override
    public ProductLocationResponseDTO getByEan(BigInteger ean) {
        Optional<Product> product = this.findByEan(ean);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product with ean " + ean + " not found.");
        }
        AddiisLogger.info("Product found: " + product.get().getObservation());
        List<WarehouseLocation> productWarehouseLocations = warehouseLocationService.findByProduct(product.get());

        if (productWarehouseLocations.isEmpty()) {
            throw new EntityNotFoundException("Location for product with ean " + ean + " not found.");
        }
        // Convertir las ubicaciones a ProductLocationDTO
        List<ProductLocationDTO> locationDTOs = productWarehouseLocations.stream()
                .map(loc -> new ProductLocationDTO(
                        loc.getCodeSap().toString(),
                        loc.getDispo())) // Cambia "Some Space" con el valor correcto si está disponible en
                                          // WarehouseLocation
                .collect(Collectors.toList());

        return new ProductLocationResponseDTO(
                product.get().getId().toString(),
                product.get().getCode().toString(),
                product.get().getEan(),
                product.get().getObservation(),
                locationDTOs,
                product.get().getCxp(),
                product.get().getUxc());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
