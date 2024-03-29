package com.addiis.core.gestionlogistica.config;

import com.addiis.core.gestionlogistica.domain.dto.ErrorLogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

/**
 * Clase controladora de logs para ser enviados a los visualizadores
 *
 * @author Arenas Silva, Juan
 * @Empresa: Addiis S.A.S.
 */

@Log4j2
public class AddiisLogger {

	public static void error(String mensaje, String clase, String metodo, String stackTrace) {

		ObjectMapper mapper = new ObjectMapper();
		ErrorLogDTO errorLog = new ErrorLogDTO(clase, metodo, stackTrace, mensaje);
		try {
			String errorLogStr = mapper.writeValueAsString(errorLog);
			log.error(errorLogStr);
		} catch (JsonProcessingException e) {
			log.error("Error construyendo el JSON del logger " + e.getMessage());
		}

	}
	
	public static void info(String mensaje) {
		log.info(mensaje);
	}
	
	public static void warn(String mensaje) {
		log.warn(mensaje);
	}
	
	

}
