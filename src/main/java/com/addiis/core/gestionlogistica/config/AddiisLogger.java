package com.addiis.core.gestionlogistica.config;

import com.addiis.core.gestionlogistica.domain.dto.ErrorLogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

/**
 * Class to manage custom logs of the application
 *
 * @author Nicolás Picón Jaimes
 * @version 1.0.0
 * @since 2024-05-04
 */

@Log4j2
public class AddiisLogger {

	public static void error(String message, String className, String method, String stackTrace) {

		ObjectMapper mapper = new ObjectMapper();
		ErrorLogDTO errorLog = new ErrorLogDTO(className, method, stackTrace, message);
		try {
			String errorLogStr = mapper.writeValueAsString(errorLog);
			log.error(errorLogStr);
		} catch (JsonProcessingException e) {
			log.error("Error construyendo el JSON del logger " + e.getMessage());
		}

	}

	public static void info(String message) {
		log.info(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}

}
