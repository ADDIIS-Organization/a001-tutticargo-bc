package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;

@Component
public class OrderPalletMapper {
  public OrderPalletsResponse toResponse(OrderPallet entity) {
    return OrderPalletsResponse.builder()
            .id(entity.getId())
            .totalPalets(entity.getTotalPallets())
            .bigPallets(entity.getBigPallets())
            .littlePallets(entity.getLittlePallets())
            .build();
  }

  public OrderPallet toEntity(OrderPalletRequest request) {
    return OrderPallet.builder()
            .totalPallets(request.getTotalPalets())
            .bigPallets(request.getBigPallets())
            .littlePallets(request.getLittlePallets())
            .build();
  }
}
