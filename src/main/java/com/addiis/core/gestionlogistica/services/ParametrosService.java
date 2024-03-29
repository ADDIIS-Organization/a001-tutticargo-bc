package com.addiis.core.gestionlogistica.services;

import java.util.List;
import java.util.Optional;

import com.addiis.core.gestionlogistica.persistence.entities.ParametrosEntity;

public interface ParametrosService {
	
	void save(ParametrosEntity parametroEntity);
	List<ParametrosEntity> findAll();
	Optional<ParametrosEntity> findByParametro(String prametro);

}
