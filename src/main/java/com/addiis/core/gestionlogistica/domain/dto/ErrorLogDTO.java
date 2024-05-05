package com.addiis.core.gestionlogistica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ErrorLogDTO {
	private String className;
	private String method;
	private String stackTrace;
	private String message;
}
