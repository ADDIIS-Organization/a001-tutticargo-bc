package com.addiis.core.gestionlogistica.services.order;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.PalletAttributes;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;

import java.util.List;

public interface OrderPalletsService {
  OrderPalletsResponse save(OrderPalletRequest request);
  OrderPalletsResponse update(OrderPalletRequest request, Long id);

  OrderPalletsResponse updateByOrderId(List<PalletAttributes> request, Long orderId);
}
