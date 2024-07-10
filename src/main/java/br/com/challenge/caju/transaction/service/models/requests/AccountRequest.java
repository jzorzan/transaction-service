package br.com.challenge.caju.transaction.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountRequest {

    private String userName;
    private String accountId;
    private BigDecimal balance;
}
