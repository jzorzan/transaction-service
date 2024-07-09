package br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String id;
    private String accountId;
    private BigDecimal amount;
    private String mcc;
    private String merchant;
}
