package br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.repositories.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionEntity {

    private String id;
    private String accountId;
    private BigDecimal amount;
    private String mcc;
    private String merchant;
}
