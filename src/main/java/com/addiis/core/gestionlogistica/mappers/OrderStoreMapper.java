package com.addiis.core.gestionlogistica.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchToOrderResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderStoreResponse;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderPalletsRepository;

@Component
public class OrderStoreMapper {

  @Autowired
  private OrderPalletsRepository orderPalletsRepository;

  public OrderStoreResponse toResponse(OrderStore entity) {

    // Obtenemos el OrderStore actual
    OrderStore currentOrderStore = entity;

    // Obtener los pallets actuales
    Set<OrderPallet> ordersPallets = entity.getOrderPallets();

    // Si ordersPallets es null o está vacío, inicializa a un conjunto vacío
    if (ordersPallets == null || ordersPallets.isEmpty()) {
      // Crear un array de pallets predeterminados
      List<OrderPallet> defaultPallets = new ArrayList<>();
      for (int i = 1; i <= 13; i++) {
        OrderPallet pallet = new OrderPallet();
        pallet.setLittlePallets(0);
        pallet.setBigPallets(0);
        pallet.setDispoId(String.valueOf(i));

        // Relacionar el pallet con el OrderStore actual
        pallet.setOrderStore(currentOrderStore);

        this.orderPalletsRepository.save(pallet);

        // Agregar el pallet a la lista predeterminada
        defaultPallets.add(pallet);
      }

      // Convertir la lista a un conjunto y asignarla a ordersPallets
      ordersPallets = new HashSet<>(defaultPallets);

      // Finalmente, relacionar los pallets con el OrderStore en ambas direcciones
      currentOrderStore.setOrderPallets(ordersPallets);
    }

    // Calcular bigPallets y littlePallets
    Integer bigPallets = ordersPallets.stream().mapToInt(OrderPallet::getBigPallets).sum();
    Integer littlePallets = ordersPallets.stream().mapToInt(OrderPallet::getLittlePallets).sum();
    Integer totalPalletsNumber = bigPallets + littlePallets;

    // Verificar si el canal asociado está presente
    Long channelId = entity.getStore().getChannel() != null ? entity.getStore().getChannel().getId() : null;
    String channelName = entity.getStore().getChannel() != null ? entity.getStore().getChannel().getNumber()
        : "default_channel_name";
    Long platformId = entity.getStore().getChannel() != null && entity.getStore().getChannel().getPlatform() != null
        ? entity.getStore().getChannel().getPlatform().getId()
        : null;
    String platformName = entity.getStore().getChannel() != null && entity.getStore().getChannel().getPlatform() != null
        ? entity.getStore().getChannel().getPlatform().getNumber()
        : "default_platform_name";

    // Log de información sobre el OrderStore
    AddiisLogger.info("The Order store is: " + entity.getId());

    // Verificación si Dispatch está presente
    Dispatch dispatch = entity.getDispatch();
    if (dispatch != null) {
      AddiisLogger.info("The Order store is: " + entity.getId() + " and the dispatch is: " + dispatch);
    } else {
      AddiisLogger.info("The Order store is: " + entity.getId() + " and the dispatch is: null");
    }

    return OrderStoreResponse.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .storeName(entity.getStore().getName())
        .storeCode(entity.getStore().getCode())
        .routeId(entity.getStore().getRoute().getId())
        .routeName(entity.getStore().getRoute().getRouteNumber())
        .channelId(channelId)
        .channelName(channelName)
        .platformId(platformId)
        .platformName(platformName)
        .littlePallets(littlePallets)
        .bigPallets(bigPallets)
        .totalPallets(totalPalletsNumber)
        .ordersPallets(ordersPallets)
        .dispatch(dispatchToOrderResponse(dispatch)) // Manejo del Dispatch, puede ser null
        .build();
  }

  public DispatchToOrderResponse dispatchToOrderResponse(Dispatch entity) {
    if (entity == null) {
      return null; // Si no hay dispatch, retorna null o una respuesta predeterminada
    }

    return DispatchToOrderResponse.builder()
        .driver(entity.getDriver())
        .vehicle(entity.getVehicle())
        .build();
  }
}
