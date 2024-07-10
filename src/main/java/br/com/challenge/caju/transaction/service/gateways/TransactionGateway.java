package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.models.dtos.TransactionDTO;
import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;

public interface TransactionGateway {

    TransactionDTO createUserBalance(final TransactionRequest request);
}
