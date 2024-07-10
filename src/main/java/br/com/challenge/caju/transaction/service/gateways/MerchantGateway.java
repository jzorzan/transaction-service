package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.models.dtos.MerchantDTO;

import java.util.Optional;

public interface MerchantGateway {

    Optional<MerchantDTO> findByMerchantName(String merchantName);
}
