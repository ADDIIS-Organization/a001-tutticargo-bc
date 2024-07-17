package com.addiis.core.gestionlogistica.services.warehouse;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface StoreService extends CRUDService<StoreRequest, StoreResponse, Long> {
  
}
