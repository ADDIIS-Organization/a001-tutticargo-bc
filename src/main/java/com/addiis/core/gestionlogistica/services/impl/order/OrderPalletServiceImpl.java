package com.addiis.core.gestionlogistica.services.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletInfo;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
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
  private final OrderRepository orderRepository;

  @Override
  public OrderPalletsResponse save(OrderPalletRequest request) {
    return null;
  }

  @Override
  public OrderPalletsResponse update(OrderPalletRequest request, Long id) {
    return null;
  }

  @Override
  public OrderPalletsResponse updateByOrderId(OrderPalletRequest request, Long orderId) {
    // Obtener la orden por su ID
    Order order = this.orderRepository.findById(orderId)
        .orElseThrow(() -> new IdNotFoundException("Order", orderId));
    // Obtener todos los OrderPallets relacionados con la orden
    Set<OrderPallet> ordersPallets = order.getOrdersPallets();
    // Crear un set para los pallets actualizados o nuevos
    Set<OrderPallet> updatedPallets = new HashSet<>();

    // Iterar sobre los atributos de los pallets en la solicitud
    for (OrderPalletInfo palletInfo : request.getOrderPalletsInfo()) {
      Optional<OrderPallet> existingPalletOpt = ordersPallets.stream()
          .filter(orderPallet -> orderPallet.getDispoId().equals(palletInfo.getDispoId().toString()))
          .findFirst();

      if (existingPalletOpt.isPresent()) {
        // Actualizar el pallet existente
        OrderPallet existingPallet = existingPalletOpt.get();
        existingPallet.setBigPallets(palletInfo.getBigPallets());
        existingPallet.setLittlePallets(palletInfo.getLittlePallets());
        updatedPallets.add(existingPallet);
      } else {
        // Crear un nuevo pallet si no existe
        OrderPallet newPallet = new OrderPallet();
        newPallet.setOrder(order);
        newPallet.setDispoId(palletInfo.getDispoId().toString());
        newPallet.setBigPallets(palletInfo.getBigPallets());
        newPallet.setLittlePallets(palletInfo.getLittlePallets());
        updatedPallets.add(newPallet);
      }
    }

    // Guardar los pallets actualizados o nuevos
    List<OrderPallet> orderPalletsUpdated = orderPalletsRepository.saveAll(updatedPallets);
    for (OrderPallet orderPallet : orderPalletsUpdated) {
      AddiisLogger.info("OrderPallet: " + orderPallet.getOrder().getId());
    }

    // Actualizar el canal, atraves de tienda
    if (request.getChannelId() != null) {
      if (order.getStore() != null && order.getStore().getChannel() != null) {
        order.getStore().getChannel().setNumber(request.getChannelId().toString());
        // Guardar la orden con el canal actualizado
        orderRepository.save(order);
        AddiisLogger.info("Channel number updated to: " + request.getChannelId());
      } else {
        AddiisLogger.warn("Store or Channel is null for Order ID: " + orderId);
      }
    }

    return new OrderPalletsResponse(orderPalletsUpdated);
  }
}