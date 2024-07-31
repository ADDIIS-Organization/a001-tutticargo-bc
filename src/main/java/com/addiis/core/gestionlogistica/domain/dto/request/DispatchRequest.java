package com.addiis.core.gestionlogistica.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchRequest {
  @NotBlank(message = "Observation is required")
  private String observation ;
  @NotNull(message = "Driver ID is required")
  private Long driverId;
  @NotNull(message = "Store ID is required")
  private Long orderStoreId;
  @NotNull(message = "Vehicle ID is required")
  private Long vehicleId;

}
