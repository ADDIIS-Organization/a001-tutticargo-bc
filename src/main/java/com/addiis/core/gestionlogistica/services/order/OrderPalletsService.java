package com.addiis.core.gestionlogistica.services.order;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;

public interface OrderPalletsService {
  OrderPalletsResponse save(OrderPalletRequest request);
  OrderPalletsResponse update(OrderPalletRequest request, Long id);
}
