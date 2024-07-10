package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.gateways.entities.TransactionEntity;
import br.com.challenge.caju.transaction.service.models.dtos.TransactionDTO;
import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.models.responses.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO entityToDto(TransactionEntity entity);

    TransactionEntity requestToEntity(TransactionRequest request);

    TransactionDTO requestToDto(TransactionRequest request);

    TransactionResponse dtoToResponse(TransactionDTO dto);
}
