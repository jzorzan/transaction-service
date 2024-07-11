package br.com.challenge.caju.transaction.service.services.accounts;

import br.com.challenge.caju.transaction.service.enums.BalanceType;

import java.math.BigDecimal;

public interface AccountService {

    boolean authorizeTransaction(final String accountId, final BigDecimal amount, final BalanceType balanceType);
}
