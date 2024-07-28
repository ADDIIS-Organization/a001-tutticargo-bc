package com.addiis.core.gestionlogistica.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ZoneRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 1, max = 45, message = "Name must be between 1 and 45 characters")
  private String name;
  @NotNull(message = "Code is required")
  private Integer code;
  @NotBlank(message = "Observation is required")
  @Size(min = 1, max = 150, message = "Observation must be between 1 and 150 characters")
  private String observation;
}
