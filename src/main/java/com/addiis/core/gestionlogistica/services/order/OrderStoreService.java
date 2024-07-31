package com.addiis.core.gestionlogistica.services.order;

import java.util.List;

import com.addiis.core.gestionlogistica.domain.dto.response.OrderStoreResponse;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface OrderStoreService extends CRUDService<OrderStore, OrderStoreResponse, Long> {
  List<OrderStoreResponse> findByStoreCode(Integer storeCode);
}
