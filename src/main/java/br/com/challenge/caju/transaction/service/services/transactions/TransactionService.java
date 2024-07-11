package br.com.challenge.caju.transaction.service.services.transactions;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionAccountResponse;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;

public interface TransactionService {

    TransactionResponse authorizeTransaction(TransactionRequest request);

    TransactionAccountResponse getTransactionsByAccount(final String accountId);
}
