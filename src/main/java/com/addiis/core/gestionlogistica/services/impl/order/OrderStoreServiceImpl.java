package com.addiis.core.gestionlogistica.services.impl.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.response.OrderStoreResponse;
import com.addiis.core.gestionlogistica.mappers.OrderStoreMapper;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderStoreRepository;
import com.addiis.core.gestionlogistica.services.order.OrderStoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderStoreServiceImpl implements OrderStoreService {

  @Autowired
  private final OrderStoreRepository orderStoreRepository;

  @Autowired
  private final OrderStoreMapper orderStoreMapper;

  @Override
  public OrderStoreResponse create(OrderStore request) {

    return null;
  }

  @Override
  public void delete(Long id) {
  }

  @Override
  public Page<OrderStoreResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;
    }
    Pageable pageable = PageRequest.of(page - 1, size);
    return this.orderStoreRepository.findAllOrderByRouteNumberAndPlatformNumber(pageable).map(orderStoreMapper::toResponse);
    // return this.orderStoreRepository.findAll(pageable).map(orderStoreMapper::toResponse);
  }

  @Override
  public List<OrderStoreResponse> findByStoreCode(Integer storeCode) {
    return orderStoreRepository.findByStoreCode(storeCode).stream().map(orderStoreMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public OrderStoreResponse findById(Long id) {

    return null;
  }

  @Override
  public OrderStoreResponse patch(OrderStore request, Long id) {

    return null;
  }

  @Override
  public OrderStoreResponse update(OrderStore request, Long id) {

    return null;
  }

}
