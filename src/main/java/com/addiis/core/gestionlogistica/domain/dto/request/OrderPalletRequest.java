package com.addiis.core.gestionlogistica.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPalletRequest {

  @NotEmpty(message = "The palletAttributes list cannot be empty")
  @Size(min = 6, message = "The palletAttributes list must have at least 6 elements")
  private List<PalletAttributes> palletAttributes;
}