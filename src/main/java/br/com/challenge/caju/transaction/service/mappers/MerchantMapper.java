package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.domains.dtos.MerchantDTO;
import br.com.challenge.caju.transaction.service.gateways.entities.Merchant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantDTO entityToDto(Merchant entity);

    Merchant dtoToEntity(MerchantDTO dto);


}
