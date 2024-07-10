package br.com.challenge.caju.transaction.service.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private String code;
    private String message;
}
