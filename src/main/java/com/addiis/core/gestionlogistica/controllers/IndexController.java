package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.persistence.entities.ParametrosEntity;
import com.addiis.core.gestionlogistica.services.ParametrosService;


@RestController
public class IndexController {
	
	@Autowired
	ParametrosService parametrosService;

	@Value("${addiis.app.version}")
	private String version;

	@Value("${addiis.app.nombre}")
	private String nombreApp;
	
	@GetMapping("/")
	public String index() {



		/*AddiisLogger.error("Hubo un error", this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());*/

		return "AppName= "+nombreApp+"<br>AppVersion="+version;
	}

}
