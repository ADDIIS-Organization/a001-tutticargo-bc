package com.addiis.core.gestionlogistica.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.DispatchRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchToOrderResponse;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderStoreRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.vehicles.DriverRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.vehicles.VehicleRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.PlatformRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.StoreRepository;

@Component
public class DispatchMapper {
  @Autowired
  private DriverMapper driverMapper;
  @Autowired
  private OrderStoreMapper orderStoreMapper;
  @Autowired
  private VehicleMapper vehicleMapper;

  @Autowired
  private DriverRepository driverRepository;
  @Autowired
  private OrderStoreRepository orderStoreRepository;
  @Autowired
  private  PlatformRepository platformRepository;
  @Autowired
  private VehicleRepository vehicleRepository;
  public DispatchResponse toResponse(Dispatch entity) {
    return DispatchResponse.builder()
            .id(entity.getId())
            .observation(entity.getObservation())
            .driver(driverMapper.toResponse(entity.getDriver()))
            .orderStore(orderStoreMapper.toResponse(entity.getOrderStore())) 
            .vehicle(vehicleMapper.toResponse(entity.getVehicle()))
            
            .build();
  }

  public Dispatch toEntity(DispatchRequest request) {
    return Dispatch.builder()
            .observation(request.getObservation())
            .driver(driverRepository.findById(request.getDriverId()).orElseThrow(() -> new RuntimeException("Driver not found")))
            .orderStore(orderStoreRepository.findById(request.getOrderStoreId()).orElseThrow(() -> new RuntimeException("Store not found")))
            .vehicle(vehicleRepository.findById(request.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found")))
            .platform(platformRepository.findById(request.getPlatformId()).orElseThrow(() -> new RuntimeException("Platform not found")))
            .build();
  }
  


}
