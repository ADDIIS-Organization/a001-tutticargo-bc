package com.addiis.core.gestionlogistica.services.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.mappers.OrderPalletMapper;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderPalletsRepository;
import com.addiis.core.gestionlogistica.services.order.OrderPalletsService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderPalletServiceImpl implements OrderPalletsService {
  
  @Autowired
  private final OrderPalletsRepository orderPalletsRepository;
  @Autowired
  private final OrderPalletMapper orderPalletMapper;

  @Override
  public OrderPalletsResponse save(OrderPalletRequest request) {
    return orderPalletMapper.toResponse(orderPalletsRepository.save(orderPalletMapper.toEntity(request)));
  }
@Override
  public OrderPalletsResponse update(OrderPalletRequest request, Long id) {
    OrderPallet entity = this.find(id);
    OrderPallet updateEntity = this.orderPalletMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.orderPalletMapper.toResponse(this.orderPalletsRepository.save(updateEntity));
  }

  private OrderPallet find(Long id) {
    OrderPallet entity = orderPalletsRepository.findById(id).orElseThrow(() -> new IdNotFoundException("OrderPallet", id));
    return entity;
  }
}
