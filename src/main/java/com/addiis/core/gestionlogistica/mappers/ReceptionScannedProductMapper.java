package com.addiis.core.gestionlogistica.mappers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.ReceptionScannedProductRequest;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.WarehouseLocationRepository;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

@Component
public class ReceptionScannedProductMapper {
  @Autowired
  private WarehouseLocationRepository warehouseLocationRepository;
  public ReceptionScannedProduct toEntity (ReceptionScannedProductRequest request) {
   WarehouseLocation warehouseLocation = warehouseLocationRepository.findById(request.getWarehouseLocationId()).orElseThrow(() -> new IdNotFoundException("Warehouse location not found" , request.getWarehouseLocationId()));
    return ReceptionScannedProduct.builder()
            .expirationDate(LocalDate.parse(request.getExpirationDate()))
            .manufactureDate(LocalDate.parse( request.getManufactureDate()))
            .usefulLife(request.getUsefulLife())
            .receptionPercentage(request.getReceptionPercentage())
            .warehouseLocation(warehouseLocation)
            .lot(request.getLot())
            .amountReceived(request.getAmountReceived())
            .SKU(request.getSKU())
            .descriptionProduct(request.getDescriptionProduct())
            .build();
  }
}
