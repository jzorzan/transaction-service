package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.AccountEntity;
import br.com.challenge.caju.transaction.service.gateways.repositories.AccountRepository;
import br.com.challenge.caju.transaction.service.mappers.AccountMapper;
import br.com.challenge.caju.transaction.service.models.dtos.AccountDTO;
import br.com.challenge.caju.transaction.service.models.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountGatewayImpl implements AccountGateway {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;


    @Override
    public AccountDTO createAccount(AccountRequest request) {

        AccountEntity entity = mapper.requestToEntity(request);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public AccountDTO getAccountById(final String accountId) {

        final var entity = repository.findById(accountId).orElse(null);
        return mapper.entityToDto(entity);
    }

    public void updateAccount(final AccountDTO dto){
        final var entity = mapper.dtoToEntity(dto);
        repository.save(entity);
    }
}
