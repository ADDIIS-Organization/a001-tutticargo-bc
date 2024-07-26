package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.response.VehicleResponse;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;
@Component
public class VehicleMapper {
  public VehicleResponse toResponse(Vehicle entity) {
    return VehicleResponse.builder()
            .id(entity.getId())
            .plate(entity.getPlate())
            .build();
  }
}
