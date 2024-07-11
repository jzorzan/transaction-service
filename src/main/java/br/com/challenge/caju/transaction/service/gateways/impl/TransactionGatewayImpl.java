package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.gateways.repositories.TransactionRepository;
import br.com.challenge.caju.transaction.service.mappers.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionGatewayImpl implements TransactionGateway {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        var entity = mapper.requestToEntity(transactionRequest);
        transactionRepository.save(entity);
    }
}
