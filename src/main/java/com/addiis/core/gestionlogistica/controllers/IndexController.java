package com.addiis.core.gestionlogistica.controllers;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

	@Autowired
	private IndexService servicioIndex;

	@GetMapping("/")
	public ResponseEntity<String> index() {
		try {
			return ResponseEntity.ok(servicioIndex.procesarInicial());
		}catch (Exception ex){
			AddiisLogger.error("Excepción no controlada", this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());

		}

		return null;
	}

}
