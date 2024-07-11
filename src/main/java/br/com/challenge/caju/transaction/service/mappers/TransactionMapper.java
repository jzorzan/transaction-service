package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.gateways.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction requestToEntity(TransactionRequest request);


}
