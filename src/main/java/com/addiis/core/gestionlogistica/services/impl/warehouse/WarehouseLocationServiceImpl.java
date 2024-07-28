package com.addiis.core.gestionlogistica.services.impl.warehouse;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.WarehouseLocationRepository;
import com.addiis.core.gestionlogistica.services.warehouse.WarehouseLocationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseLocationServiceImpl implements WarehouseLocationService{

    @Autowired
    private final WarehouseLocationRepository warehouseLocationRepository;

    public WarehouseLocationServiceImpl(WarehouseLocationRepository warehouseLocationRepository) {
        this.warehouseLocationRepository = warehouseLocationRepository;
    }

    @Override
    public List<WarehouseLocation> findByProduct(Product product) {
        return warehouseLocationRepository.findByProduct(product);
    }

    @Override
    public Optional<WarehouseLocation> findById(Long id) {
        return warehouseLocationRepository.findById(id);
    }
    
}
