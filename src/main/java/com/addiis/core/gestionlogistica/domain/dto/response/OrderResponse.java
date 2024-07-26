package com.addiis.core.gestionlogistica.domain.dto.response;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private BigInteger orderNumber;
    private Integer storeCode;
    private Integer routeCode;
    private String channelNumber;
    private String storeName;
    private String platformNumber;
    private Timestamp date;
    private Set<OrderPallet> ordersPallets;
    private Integer bigPallets;
    private Integer littlePallets;
    private Integer totalPalletsNumber;
    private String routeName;

    // Definir explícitamente el constructor
    public OrderResponse(Long id, BigInteger orderNumber, Integer storeCode, Integer routeCode, String channelNumber, 
                         String storeName,String platformNumber ,Timestamp date, Set<OrderPallet> ordersPallets, 
                         Integer bigPallets, Integer littlePallets, Integer totalPalletsNumber,String routeName) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.storeCode = storeCode;
        this.routeCode = routeCode;
        this.channelNumber = channelNumber;
        this.storeName = storeName;
        this.date = date;
        this.ordersPallets = ordersPallets;
        this.bigPallets = bigPallets;
        this.littlePallets = littlePallets;
        this.totalPalletsNumber = totalPalletsNumber;
        this.routeName = routeName;
        this.platformNumber = platformNumber;
    }
}