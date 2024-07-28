package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.ZoneRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ZoneResponse;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Zone;

@Component
public class ZoneMapper {
  
  public ZoneResponse toResponse(Zone entity) { 
    return ZoneResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .code(entity.getCode())
            .observation(entity.getObservation()) 
            .build();
  }

  public Zone toEntity(ZoneRequest request) {
    return Zone.builder()
            .name(request.getName())
            .code(request.getCode())
            .observation(request.getObservation())
            .build();
  }
}
