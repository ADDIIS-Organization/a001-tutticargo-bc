package com.addiis.core.gestionlogistica.services.warehouse;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.CustomStoreResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

import org.springframework.data.domain.Page;

public interface StoreService extends CRUDService<StoreRequest, StoreResponse, Long> {
    Page<CustomStoreResponse> findAllCustom(int page, int size);
}
