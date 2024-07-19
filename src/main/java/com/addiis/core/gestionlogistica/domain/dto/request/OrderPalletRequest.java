package com.addiis.core.gestionlogistica.domain.dto.request;

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
public class OrderPalletRequest {
  private Integer bigPallets;
  private Integer littlePallets;
  private Integer totalPalets;
}
