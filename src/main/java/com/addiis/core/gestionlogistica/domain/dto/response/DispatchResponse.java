package com.addiis.core.gestionlogistica.domain.dto.response;

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
  private StoreResponse store;
  private VehicleResponse vehicle;
}
