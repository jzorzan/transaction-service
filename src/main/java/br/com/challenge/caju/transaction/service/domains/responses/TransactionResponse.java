package br.com.challenge.caju.transaction.service.domains.responses;

import br.com.challenge.caju.transaction.service.enums.TransactionCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private String code;
}
