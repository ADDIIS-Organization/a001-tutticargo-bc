package com.addiis.core.gestionlogistica.domain.dto.response;

import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;

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
public class DispatchToOrderResponse {
  private String observation;
  private Driver driver;
  private Vehicle vehicle;
}
