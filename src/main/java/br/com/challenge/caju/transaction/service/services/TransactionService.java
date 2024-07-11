package br.com.challenge.caju.transaction.service.services;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;

public interface TransactionService {

    TransactionResponse authorizeTransaction(TransactionRequest request);


}
