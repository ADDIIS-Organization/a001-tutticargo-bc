package com.addiis.core.gestionlogistica.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

import com.addiis.core.gestionlogistica.response.ApiResponse;

@Aspect
@Component
public class ApiResponseAspect {

    @Pointcut("@annotation(com.addiis.core.gestionlogistica.annotation.ApiResponseWrapper)")
    public void annotatedWithApiResponseWrapper() {
    }

    @Around("annotatedWithApiResponseWrapper()")
    public Object wrapResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if (!(result instanceof ResponseEntity)) {
            throw new IllegalArgumentException(
                    "ApiResponseWrapper should be used with methods returning ResponseEntity");
        }

        ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
        Object responseBody = responseEntity.getBody();
        boolean isSuccess = responseEntity.getStatusCode().is2xxSuccessful();

        if (isSuccess) {
            ApiResponse<Object> apiResponse = ApiResponse.ok(responseBody); // Wrap the plain data
            return ResponseEntity.status(responseEntity.getStatusCode()).body(apiResponse);
        } else {
            String errorMessage = responseBody instanceof String ? (String) responseBody : "Request failed";
            ApiResponse<Object> apiError = ApiResponse.noStringError(errorMessage); // Wrap the plain error message
            return ResponseEntity.status(responseEntity.getStatusCode()).body(apiError);
        }
    }

}
