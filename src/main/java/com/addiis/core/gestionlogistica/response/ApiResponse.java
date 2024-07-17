package com.addiis.core.gestionlogistica.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Map<String, Object> errors = new HashMap<>();
    private Map<String, Object> metadata = new HashMap<>();
    private Map<String, Object> links = new HashMap<>();
    private Map<String, Object> included = new HashMap<>();
    private Map<String, Object> pageable = new HashMap<>();

    // Simplified constructors for common response patterns
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean success, String message, T data, Map<String, Object> metadata) {
        this(success, message, data);
        this.metadata = metadata;
    }

    public ApiResponse(boolean success, String message, T data, Map<String, Object> metadata, Map<String, Object> links, Map<String, Object> included) {
        this(success, message, data, metadata);
        this.links = links;
        this.included = included;
    }

    // Static factory methods to create instances
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public static <T> ApiResponse<T> errorWithDetails(String message, Map<String, Object> errors) {
        ApiResponse<T> response = new ApiResponse<>(false, message, null, errors);
        return response;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "Request successful", data);
    }

    public static <T> ApiResponse<T> okWithMetadata(T data, Map<String, Object> metadata) {
        return new ApiResponse<>(true, "Request successful", data, metadata);
    }

    public static <T> ApiResponse<T> okFull(T data, Map<String, Object> metadata, Map<String, Object> links, Map<String, Object> included) {
        return new ApiResponse<>(true, "Request successful", data, metadata, links, included);
    }

}
