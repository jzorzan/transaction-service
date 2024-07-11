package br.com.challenge.caju.transaction.service.gateways.repositories;

import br.com.challenge.caju.transaction.service.gateways.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
