package com.addiis.core.gestionlogistica.services.dispatch;

import com.addiis.core.gestionlogistica.domain.dto.request.DispatchRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface DispatchService extends CRUDService<DispatchRequest, DispatchResponse, Long> {
  
}
