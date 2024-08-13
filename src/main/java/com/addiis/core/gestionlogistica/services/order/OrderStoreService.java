package com.addiis.core.gestionlogistica.services.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.addiis.core.gestionlogistica.domain.dto.response.OrderStoreResponse;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface OrderStoreService extends CRUDService<OrderStore, OrderStoreResponse, Long> {
  List<OrderStoreResponse> findByStoreCode(Integer storeCode);

  Page<OrderStoreResponse> findAllOrderByRouteNumber(int page , int size  , LocalDate date);
  Page<OrderStoreResponse> findByRouteAndStoreCode(int page , int size ,String route , Integer storeCode , LocalDate date);
}
