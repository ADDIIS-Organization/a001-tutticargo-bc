package com.addiis.core.gestionlogistica.services.order;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface OrderService extends CRUDService<OrderRequest, OrderResponse, Long> {
    
}
