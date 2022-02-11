package ai.ecma.redisApp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public static RestException restThrow(String message, HttpStatus status) {
        return new RestException(message, status);
    }

    /**
     * @param resourceKey - {@link org.springframework.context.MessageSource} bo'yicha kelishi kerak. Masalan "GROUP"
     * @return Guruh topilmadi!
     */
    public static RestException notFound(String message) {
        return new RestException(message, HttpStatus.NOT_FOUND);
    }

    public static RestException alreadyExists(String message) {
        return new RestException(message, HttpStatus.CONFLICT);
    }
}
