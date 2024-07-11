package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.gateways.entities.Account;

import java.util.Optional;

public interface AccountGateway {

    Optional<Account> findById(final String accountId);

    void updateAccount(Account dto);

}
