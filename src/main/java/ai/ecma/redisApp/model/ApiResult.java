package ai.ecma.redisApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Muhammad Mo'minov
 * 06.11.2021
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;


    private ApiResult(Boolean success) {
        this.success = success;
    }

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResult(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(true, message, data);
    }

    public static <E> ApiResult<E> successResponse() {
        return new ApiResult<>(true);
    }

    public static <E> ApiResult<E> successResponse(E data) {
        return new ApiResult<>(data, true);
    }

    public static ApiResult<String> successResponse(String message) {
        return new ApiResult<>(true, message);
    }
}
