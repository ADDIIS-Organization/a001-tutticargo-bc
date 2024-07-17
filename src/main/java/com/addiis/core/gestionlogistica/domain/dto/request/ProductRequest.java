package com.addiis.core.gestionlogistica.domain.dto.request;

import java.math.BigInteger;

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
public class ProductRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 1, max = 45, message = "Name must be between 1 and 45 characters")
  private String name;
  @NotNull(message = "Code is required")
  private BigInteger code;
  @NotNull(message = "EAN is required")
  private BigInteger ean;
  @NotBlank(message = "UXC is required")
  @Size(min = 1, max = 45, message = "UXC must be between 1 and 45 characters")
  private String uxc;
  @NotBlank(message = "CXP is required")
  @Size(min = 1, max = 45, message = "CXP must be between 1 and 45 characters")
  private String cxp;
  @NotBlank(message = "Observation is required")
  @Size(min = 1, max = 100, message = "Observation must be between 1 and 100 characters")
  private String observation;
}
