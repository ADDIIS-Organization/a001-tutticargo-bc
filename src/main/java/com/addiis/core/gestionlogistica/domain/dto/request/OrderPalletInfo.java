package com.addiis.core.gestionlogistica.domain.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class OrderPalletInfo {

  @NotNull(message = "bigPallets cannot be null")
  private Integer bigPallets;

  @NotNull(message = "littlePallets cannot be null")
  private Integer littlePallets;

  @NotNull(message = "dispoId cannot be null")
  private Integer dispoId;
}