package com.addiis.core.gestionlogistica.services.impl.product;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ProductLocationDTO;
import com.addiis.core.gestionlogistica.domain.dto.request.ProductRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductResponse;
import com.addiis.core.gestionlogistica.mappers.ProductMapper;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.products.ProductRepository;
import com.addiis.core.gestionlogistica.services.product.ProductService;
import com.addiis.core.gestionlogistica.services.warehouse.WarehouseLocationService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final WarehouseLocationService warehouseLocationService;
    @Autowired
    private final ProductMapper productMapper;

    @Override
    public void save(ProductRequest request) {
        Product entity = productMapper.toEntity(request);
        Product productCreated = productRepository.save(entity);
        AddiisLogger.info("Almacenando Material " + productCreated.getObservation());
    }

    @Override
    public Page<ProductResponse> findAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);
        return this.productRepository.findAll(pageable).map(productMapper::toResponse);

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
                        loc.getId(),
                        loc.getCodeSap().toString(),
                        loc.getDispo())) // Cambia "Some Space" con el valor correcto si está disponible en
                         // Cambia "Some Space" con el valor correcto si está disponible en
                                          // WarehouseLocation
                .collect(Collectors.toList());

        return new ProductLocationResponseDTO(
                product.get().getId().toString(),
                product.get().getCode().toString(),
                product.get().getEan(),
                product.get().getObservation(),
                locationDTOs,
                product.get().getCxp(),
                product.get().getUxc(),
                product.get().getWarehouseLocation().getId()
                );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductResponse update(ProductRequest request, Long id) {
        Product entity = this.find(id);
        Product updateEntity = this.productMapper.toEntity(request);
        updateEntity.setId(entity.getId());
        return this.productMapper.toResponse(this.productRepository.save(updateEntity));
    }

    @Override
    public void delete(Long id) {
        Product entity = find(id);
        this.productRepository.delete(entity);

    }

    @Override
    public ProductResponse disableProduct (Long id) {
        Product entity = find(id);
        entity.setActive(!entity.isActive());
        return this.productMapper.toResponse(this.productRepository.save(entity));
    }

    private Product find(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Product", id));
        return entity;

    }
}
