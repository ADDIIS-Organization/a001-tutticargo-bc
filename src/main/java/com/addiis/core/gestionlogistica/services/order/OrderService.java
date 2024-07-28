package com.addiis.core.gestionlogistica.services.order;

import java.util.List;

import org.springframework.data.domain.Page;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface OrderService extends CRUDService<OrderRequest, OrderResponse, Long> {
  List<OrderResponse> findByStoreCode(Integer storeCode);
}
