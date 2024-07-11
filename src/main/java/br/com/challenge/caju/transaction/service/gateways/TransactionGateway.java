package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;

public interface TransactionGateway {


    void saveTransaction(final TransactionRequest transactionRequest);
}
