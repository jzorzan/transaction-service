package br.com.challenge.caju.transaction.service.gateways.impl;

import br.com.challenge.caju.transaction.service.gateways.MerchantGateway;
import br.com.challenge.caju.transaction.service.gateways.repositories.MerchantRepository;
import br.com.challenge.caju.transaction.service.mappers.MerchantMapper;
import br.com.challenge.caju.transaction.service.models.dtos.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantGatewayImpl implements MerchantGateway {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Optional<MerchantDTO> findByMerchantName(final String merchantName) {
        final var entity = merchantRepository.findByMerchantName(merchantName).orElse(null);
        return Optional.of(merchantMapper.entityToDto(entity));
    }
}
