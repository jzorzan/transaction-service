package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Transaction;
import br.com.challenge.caju.transaction.service.gateways.repositories.TransactionRepository;
import br.com.challenge.caju.transaction.service.mappers.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.FOUND_TRANSACTIONS_ACCOUNT;
import static br.com.challenge.caju.transaction.service.utils.constants.Constants.STARTING_SEARCH_TRANSACTION_ACCOUNT;

@Service
@Slf4j
public class TransactionGatewayImpl implements TransactionGateway {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    @Transactional
    public void saveTransaction(TransactionRequest transactionRequest) {
        var entity = mapper.requestToEntity(transactionRequest);
        transactionRepository.save(entity);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactionByAccount(final String accountId) {

        log.info(STARTING_SEARCH_TRANSACTION_ACCOUNT, accountId);

        final var transactions = transactionRepository.findByAccountId(accountId);

        log.info(FOUND_TRANSACTIONS_ACCOUNT, transactions.size(), accountId);
        return transactions;
    }
}
