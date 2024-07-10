package br.com.challenge.caju.transaction.service.services;

import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.models.responses.TransactionResponse;

public interface TransactionService {

    TransactionResponse processTransaction(TransactionRequest request);


}
