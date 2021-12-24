package ai.ecma.springbootredis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: Muhammad Mo'minov
 * 10.06.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse implements Serializable {
    boolean success;
    String message;
    Object object;

    public static ApiResponse success(String message) {
        return new ApiResponse(true, message, new Object());
    }

    public static ApiResponse success(Object object) {
        return new ApiResponse(true, "", object);
    }

    public static ApiResponse success(String message, Object object) {
        return new ApiResponse(true, message, object);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(false, message, new Object());
    }

    public static ApiResponse error(Object object) {
        return new ApiResponse(false, "", object);
    }

    public static ApiResponse error(String message, Object object) {
        return new ApiResponse(false, message, object);
    }

}
