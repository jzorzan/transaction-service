package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.gateways.repositories.TransactionRepository;
import br.com.challenge.caju.transaction.service.mappers.TransactionMapper;
import br.com.challenge.caju.transaction.service.models.dtos.TransactionDTO;
import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionGatewayImpl implements TransactionGateway {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    @Transactional
    public TransactionDTO createUserBalance(TransactionRequest request) {

        var entity = mapper.requestToEntity(request);
        entity = transactionRepository.save(entity);
        return mapper.entityToDto(entity);
    }
}
