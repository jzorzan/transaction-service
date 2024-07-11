package br.com.challenge.caju.transaction.service.gateways;

import br.com.challenge.caju.transaction.service.gateways.entities.Merchant;

import java.util.Optional;

public interface MerchantGateway {

    Optional<Merchant> findByMerchantName(String merchantName);
}
