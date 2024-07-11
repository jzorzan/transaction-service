package br.com.challenge.caju.transaction.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TransactionNotFound.class, AccountNotFound.class})
    @ResponseBody
    protected ResponseEntity<ErrorMessage> handleNotFound(TransactionNotFound ex){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("07")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(value = {InvalidFieldException.class})
    @ResponseBody
    protected ResponseEntity<ErrorMessage> handleInvalidField(TransactionNotFound ex){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("07")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
    }
}
