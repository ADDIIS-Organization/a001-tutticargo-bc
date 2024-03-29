package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.persistence.entities.ParametrosEntity;
import com.addiis.core.gestionlogistica.services.ParametrosService;


@RestController
public class IndexController {
	
	@Autowired
	ParametrosService parametrosService;
	
	@GetMapping("/")
	public String index() {
		
		ParametrosEntity entidad = new ParametrosEntity();
		entidad.setParametro("Pruebas");
		entidad.setNumero1(1);
		
		try {
			float edad = 12/0;
		}catch (Exception e) {
			AddiisLogger.error("Hubo un error", this.getClass().getName(), 
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
				
		parametrosService.save(entidad);
		return "Hola";
	}

}
