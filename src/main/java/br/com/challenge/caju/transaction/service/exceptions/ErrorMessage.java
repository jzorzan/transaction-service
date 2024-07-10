package br.com.challenge.caju.transaction.service.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    private String code;
    private String message;
}
