package br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private String code;
    private String message;
}
