package com.addiis.core.gestionlogistica.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class ProductTypeRequest {
  @NotBlank(message = "Product type name is required")
  @Size(min = 1, max = 45, message = "Product type name must be between 1 and 45 characters") 
  private String name;
  @NotBlank(message = "Product type observation is required")
  @Size(min = 1, max = 150, message = "Product type observation must be between 1 and 150 characters")
  private String observation;

}
