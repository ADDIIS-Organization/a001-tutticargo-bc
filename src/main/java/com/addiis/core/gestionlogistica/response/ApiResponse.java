package com.addiis.core.gestionlogistica.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Map<String, Object> errors;
    private Map<String, Object> metadata;
    private Map<String, Object> links;
    private Map<String, Object> included;

    // Error response
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Regular ok response
    public ApiResponse(boolean success, String message, T data, Map<String, Object> metadata){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Pagination response
    public ApiResponse(boolean success, String message, T data, Map<String, Object> metadata, Map<String, Object> links, Map<String, Object> included){
        this.success = success;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
        this.links = links;
        this.included = included;
    }


    public static <T> ApiResponse<T> noStringError(String message) {
        return new ApiResponse<>(false, message);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(false, message, data, new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "Request successful", data, new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    // public static <T> ApiResponse<T> ok(T data) {
    //     return new ApiResponse<>(true, "Request successful", data, new HashMap<>());
    // }
}
