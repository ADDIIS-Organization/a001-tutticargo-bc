package com.addiis.core.gestionlogistica.persistence.repositories.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;

import java.util.List;

public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, Long>{
    List<WarehouseLocation> findByProduct(Product product);
}
