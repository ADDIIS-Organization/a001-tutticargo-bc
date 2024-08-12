package com.addiis.core.gestionlogistica.services.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletInfo;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderPalletsRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderStoreRepository;
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
  private final OrderStoreRepository orderStoreRepository;

  @Override
  public OrderPalletsResponse save(OrderPalletRequest request) {
    return null;
  }

  @Override
  public OrderPalletsResponse update(OrderPalletRequest request, Long id) {
    return null;
  }

  @Override
  public OrderPalletsResponse updateByOrderStoreId(OrderPalletRequest request, Long orderStoreId) {
    // Obtener la orden por su ID
    OrderStore orderStore = this.orderStoreRepository.findById(orderStoreId)
        .orElseThrow(() -> new IdNotFoundException("OrderStore", orderStoreId));

    // Obtener todos los OrderPallets relacionados con la orden
    Set<OrderPallet> ordersPallets = orderStore.getOrderPallets();

    // Crear un set para los pallets actualizados o nuevos
    Set<OrderPallet> updatedPallets = new HashSet<>();

    // Iterar sobre los atributos de los pallets en la solicitud
    for (OrderPalletInfo palletInfo : request.getOrderPalletsInfo()) {
      Integer palletDispoId = palletInfo.getDispoId();
      boolean palletFound = false;

      for (OrderPallet existingPallet : ordersPallets) {
        if (palletDispoId.equals(existingPallet.getDispoId())) {
          // Actualizar el pallet existente
          existingPallet.setBigPallets(palletInfo.getBigPallets());
          existingPallet.setLittlePallets(palletInfo.getLittlePallets());
          updatedPallets.add(existingPallet);
          palletFound = true;
          break; // Salir del bucle una vez que se haya actualizado el pallet
        }
      }

      if (!palletFound) {
        // Crear un nuevo pallet si no existe
        OrderPallet newPallet = new OrderPallet();
        newPallet.setDispoId(palletDispoId);
        newPallet.setBigPallets(palletInfo.getBigPallets());
        newPallet.setLittlePallets(palletInfo.getLittlePallets());
        newPallet.setOrderStore(orderStore); // Asegúrate de asociar el nuevo pallet con la orden
        updatedPallets.add(newPallet);
      }
    }

    // Guardar los pallets actualizados o nuevos
    List<OrderPallet> orderPalletsUpdated = orderPalletsRepository.saveAll(updatedPallets);

    return new OrderPalletsResponse(orderPalletsUpdated);
  }

}
