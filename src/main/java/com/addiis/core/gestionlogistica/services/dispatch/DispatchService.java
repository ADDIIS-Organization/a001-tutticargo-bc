package com.addiis.core.gestionlogistica.services.dispatch;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.addiis.core.gestionlogistica.domain.dto.request.DispatchRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchResponse;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface DispatchService extends CRUDService<DispatchRequest, DispatchResponse, Long> {
  Page<DispatchResponse> findDispatchesByDate(LocalDate dispatchDate , int page, int size);
}
