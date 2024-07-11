package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.gateways.entities.Transaction;

import java.util.List;

public interface TransactionGateway {

    void saveTransaction(final TransactionRequest transactionRequest);

    List<Transaction> getTransactionByAccount(final String accountId);
}
