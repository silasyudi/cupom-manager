package yudi.silas.listener;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import yudi.silas.exception.CupomExisteException;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class ExceptionListener extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        Map<String, Object> response = new HashMap<>();
        response.put("exception", message);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            CupomExisteException.class,
    })
    public final ResponseEntity<Map<String, Object>> handleDomainException(Exception exception, WebRequest request) {
        return handle(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, Object>> handleGenericException(Exception exception, WebRequest request) {
        return handle(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> handle(Throwable exception, HttpStatus status) {
        return handle(exception.getMessage(), status);
    }

    private ResponseEntity<Map<String, Object>> handle(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("exception", message);
        return new ResponseEntity<>(response, status);
    }
}
