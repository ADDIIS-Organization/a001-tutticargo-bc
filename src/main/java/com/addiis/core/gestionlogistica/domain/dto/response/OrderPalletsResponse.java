package com.addiis.core.gestionlogistica.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPalletsResponse {
  private List<OrderPallet> pallets;
}
