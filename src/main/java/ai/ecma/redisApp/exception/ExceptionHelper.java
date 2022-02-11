package ai.ecma.redisApp.exception;

import ai.ecma.redisApp.model.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Muhammad Mo'minov
 * 06.11.2021
 */
@RestControllerAdvice
public class ExceptionHelper {
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<?> handleException(RestException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(ex.getStatus()).body(new ApiResult<>(false, ex.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResult<>(false, "Server error, Please try again"));
    }


}
