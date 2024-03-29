package com.addiis.core.gestionlogistica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ErrorLogDTO {
	private String claseError;
	private String metodoError;
	private String pilaTrazas;
	private String mensaje;
	
}
