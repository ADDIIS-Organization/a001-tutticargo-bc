package com.addiis.core.gestionlogistica.services.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.PalletAttributes;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.mappers.OrderPalletMapper;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderPalletsRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderRepository;
import com.addiis.core.gestionlogistica.services.order.OrderPalletsService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderPalletServiceImpl implements OrderPalletsService {

  @Autowired
  private final OrderPalletsRepository orderPalletsRepository;
  @Autowired
  private final OrderPalletMapper orderPalletMapper;
  @Autowired
  private final OrderRepository orderRepository;

  @Override
  public OrderPalletsResponse save(OrderPalletRequest request) {
    // return orderPalletMapper.toResponse(orderPalletsRepository.save(orderPalletMapper.toEntity(request)));
    return null;
  }

  @Override
  public OrderPalletsResponse update(OrderPalletRequest request, Long id) {
    return null;
    // OrderPallet entity = this.find(id);
    // OrderPallet updateEntity = this.orderPalletMapper.toEntity(request);
    // updateEntity.setId(entity.getId());
    // return this.orderPalletMapper.toResponse(this.orderPalletsRepository.save(updateEntity));
  }

  private OrderPallet find(Long id) {
    return null;
    // OrderPallet entity = orderPalletsRepository.findById(id)
    //     .orElseThrow(() -> new IdNotFoundException("OrderPallet", id));
    // return entity;
  }

  @Override
  public OrderPalletsResponse updateByOrderId(List<PalletAttributes> request, Long orderId) {
      // Obtener la orden por su ID
      Order order = this.orderRepository.findById(orderId)
          .orElseThrow(() -> new IdNotFoundException("Order", orderId));
      // Obtener todos los OrderPallets relacionados con la orden
      Set<OrderPallet> ordersPallets = order.getOrdersPallets();
      for (OrderPallet orderPallet : ordersPallets) {
        AddiisLogger.info("OrderPallet from line 66: " + orderPallet);
      }
      for (PalletAttributes palletAttributes : request) {
        AddiisLogger.info("PalletAttributes from line 69: " + palletAttributes.getDispoId());
      }
      // Crear un set para los pallets actualizados o nuevos
      Set<OrderPallet> updatedPallets = new HashSet<>();
      
      // Iterar sobre los atributos de los pallets en la solicitud
      for (PalletAttributes palletAttributes : request) {
        Optional<OrderPallet> existingPalletOpt = ordersPallets.stream()
        .filter(orderPallet -> orderPallet.getDispoId().equals(palletAttributes.getDispoId()))
        .findFirst();
        
        if (existingPalletOpt.isPresent()) {
          // Actualizar el pallet existente
          OrderPallet existingPallet = existingPalletOpt.get();
          existingPallet.setBigPallets(palletAttributes.getBigPallets());
          existingPallet.setLittlePallets(palletAttributes.getLittlePallets());
          updatedPallets.add(existingPallet);
          AddiisLogger.info("it got here from if");
        } else {
          AddiisLogger.info("it got here from else");
          // Crear un nuevo pallet si no existe
          OrderPallet newPallet = new OrderPallet();
          newPallet.setOrder(order);
          newPallet.setDispoId(palletAttributes.getDispoId());
          newPallet.setBigPallets(palletAttributes.getBigPallets());
          newPallet.setLittlePallets(palletAttributes.getLittlePallets());
          updatedPallets.add(newPallet);
        }
      }

      // AddiisLogger.info("it got here right after conditions");
      
      // Guardar los pallets actualizados o nuevos
      List<OrderPallet> orderPalletsUpdated = orderPalletsRepository.saveAll(updatedPallets);
      for (OrderPallet orderPallet : orderPalletsUpdated) {
        AddiisLogger.info("OrderPallet: " + orderPallet);
      }
  
      return new OrderPalletsResponse(orderPalletsUpdated);
  }
  
}
