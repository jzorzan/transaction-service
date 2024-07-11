package br.com.challenge.caju.transaction.service.gateways.repositories;

import br.com.challenge.caju.transaction.service.gateways.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Optional<Merchant> findByMerchantName(String merchantName);
}
