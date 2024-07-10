package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.models.dtos.AccountDTO;
import br.com.challenge.caju.transaction.service.models.requests.AccountRequest;

public interface AccountGateway {

    AccountDTO createAccount(AccountRequest request);

    AccountDTO getAccountById(final String accountId);
    void updateAccount(AccountDTO dto);

}
