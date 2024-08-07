package com.addiis.core.gestionlogistica.domain.dto.response;

import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Platform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DispatchResponse {
  private Long id;
  private String observation;
  private DriverResponse driver;
  private OrderStoreResponse orderStore;
  private VehicleResponse vehicle;
 
}
