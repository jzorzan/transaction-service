package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Account;
import br.com.challenge.caju.transaction.service.gateways.repositories.AccountRepository;
import br.com.challenge.caju.transaction.service.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountGatewayImpl implements AccountGateway {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public Optional<Account> findById(final String accountId) {
        return repository.findById(accountId);
    }

    public void updateAccount(final Account account){
        repository.save(account);
    }
}
