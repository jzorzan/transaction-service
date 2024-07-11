package br.com.challenge.caju.transaction.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class InvalidFieldException extends RuntimeException {

    private String message;
    private Set<String> fields;
}
