package br.com.challenge.caju.transaction.service.mappers;

import br.com.challenge.caju.transaction.service.gateways.entities.MerchantEntity;
import br.com.challenge.caju.transaction.service.models.dtos.MerchantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantDTO entityToDto(MerchantEntity entity);

    MerchantEntity dtoToEntity(MerchantDTO dto);


}
