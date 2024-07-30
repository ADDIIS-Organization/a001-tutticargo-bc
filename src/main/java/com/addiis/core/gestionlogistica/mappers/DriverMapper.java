package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.response.DriverResponse;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;

@Component
public class DriverMapper {
  
  public DriverResponse toResponse(Driver entity) {
    return DriverResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .document(entity.getDocument())
            .active(entity.getActive())
            .build();
  }
}
