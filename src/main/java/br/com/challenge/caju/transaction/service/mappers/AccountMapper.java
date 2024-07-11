package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.domains.responses.AccountResponse;
import br.com.challenge.caju.transaction.service.gateways.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse entityToResponse(Account entity);


}
