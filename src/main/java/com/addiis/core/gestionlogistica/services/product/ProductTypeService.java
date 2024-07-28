package com.addiis.core.gestionlogistica.services.product;

import com.addiis.core.gestionlogistica.domain.dto.request.ProductTypeRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductTypeResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface ProductTypeService extends CRUDService<ProductTypeRequest, ProductTypeResponse, Long> {
  
}
