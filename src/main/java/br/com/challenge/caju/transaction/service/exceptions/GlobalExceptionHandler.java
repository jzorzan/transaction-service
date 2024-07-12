package br.com.challenge.caju.transaction.service.exceptions;

import br.com.challenge.caju.transaction.service.enums.TransactionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.ERROR_MESSAGE_MISSING_FIELDS;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidFieldException.class})
    @ResponseBody
    protected ResponseEntity<ErrorMessage> handleInvalidField(InvalidFieldException ex) {

        log.error(ERROR_MESSAGE_MISSING_FIELDS, ex.getFields());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(TransactionCode.NOT_PROCESSED.getCode())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
    }
}
