package com.minhvq.orchidservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int statusCode;

    // Success response with data
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, 200);
    }

    // Success response with data and custom status
    public static <T> ApiResponse<T> success(T data, String message, int statusCode) {
        return new ApiResponse<>(true, message, data, statusCode);
    }

    // Success response without data
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null, 200);
    }

    // Error response
    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return new ApiResponse<>(false, message, null, statusCode);
    }

    // Error response with default 400 status
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, 400);
    }
}
