package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.gateways.entities.AccountEntity;
import br.com.challenge.caju.transaction.service.models.dtos.AccountDTO;
import br.com.challenge.caju.transaction.service.models.requests.AccountRequest;
import br.com.challenge.caju.transaction.service.models.responses.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO entityToDto(AccountEntity entity);

    AccountEntity dtoToEntity(AccountDTO dto);

    AccountEntity requestToEntity(AccountRequest request);

    AccountResponse dtoToResponse(AccountDTO dto);
}
