package com.addiis.core.gestionlogistica.domain.dto.response;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStoreResponse {
    private Long id;
    private Timestamp date;
    private String storeName;
    private Integer storeCode;
    private Long routeId;
    private String routeName;
    private Long channelId;
    private String channelName;
    private Long platformId;
    private String platformName;
    private Integer bigPallets;
    private Integer littlePallets;
    private Integer totalPallets;
    private Set<OrderPallet> ordersPallets;

}
