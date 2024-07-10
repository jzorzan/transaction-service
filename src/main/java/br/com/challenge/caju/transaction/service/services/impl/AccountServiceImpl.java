package br.com.challenge.caju.transaction.service.services.impl;

import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.mappers.AccountMapper;
import br.com.challenge.caju.transaction.service.models.requests.AccountRequest;
import br.com.challenge.caju.transaction.service.models.responses.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private AccountMapper accountMapper;

    public AccountResponse createAccount(final AccountRequest request){

        final var accountCreated = accountGateway.createAccount(request);
        return accountMapper.dtoToResponse(accountCreated);
    }

    public AccountResponse getTransactionsByAccount(final String accountId){
        final var dto = accountGateway.getAccountById(accountId);
        return accountMapper.dtoToResponse(dto);
    }
}
