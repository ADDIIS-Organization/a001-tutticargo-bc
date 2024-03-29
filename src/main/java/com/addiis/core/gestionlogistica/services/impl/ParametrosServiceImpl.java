package com.addiis.core.gestionlogistica.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.persistence.entities.ParametrosEntity;
import com.addiis.core.gestionlogistica.persistence.repositories.ParametrosRepository;
import com.addiis.core.gestionlogistica.services.ParametrosService;


@Service
public class ParametrosServiceImpl implements ParametrosService {
	
	@Autowired
	ParametrosRepository parametroRepository;

	@Override
	public List<ParametrosEntity> findAll() {
		return parametroRepository.findAll();
	}

	@Override
	public void save(ParametrosEntity parametroEntity) {
		parametroRepository.save(parametroEntity);
		
	}

	@Override
	public Optional<ParametrosEntity> findByParametro(String prametro) {
		return parametroRepository.findByParametro(prametro);
	}


}
