package iamcoda.example.springwebservicedemo.exception;

import iamcoda.example.springwebservicedemo.rest.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ErrorHandling exception = new ErrorHandling(LocalDateTime.now(),
                                    ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ErrorHandling exception = new ErrorHandling(LocalDateTime.now(),
                                    ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorHandling exception = new ErrorHandling(LocalDateTime.now(),
                ex.getFieldError().getDefaultMessage(), request.getDescription(false));


        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);

     }
}
