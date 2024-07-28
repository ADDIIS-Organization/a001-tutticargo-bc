package com.addiis.core.gestionlogistica.services;

import org.springframework.data.domain.Page;
//RQ = Request, RS = Response, ID = Identifier
public interface CRUDService <RQ, RS, ID> {
  
  RS create(RQ request);
  
  RS update(RQ request, Long id);
  
  RS findById(ID id);

  Page<RS> findAll(int page, int size);

  void delete(ID id);

  RS patch(RQ request, ID id);
  
}
