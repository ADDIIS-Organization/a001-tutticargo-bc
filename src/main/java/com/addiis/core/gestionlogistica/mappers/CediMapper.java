package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.CediRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.CediResponse;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Cedi;
@Component
public class CediMapper {
  public CediResponse toResponse(Cedi entity) {
    return CediResponse.builder()
            .name(entity.getName())
            .code(entity.getCode())
            .city(entity.getCity())
            .observation(entity.getObservation())
            .build();
  }

  public Cedi toEntity(CediRequest request) {
    return Cedi.builder()
            .name(request.getName())
            .code(request.getCode())
            .city(request.getCity())
            .observation(request.getObservation())
            .build();
  }
}
