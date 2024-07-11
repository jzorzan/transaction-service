package br.com.challenge.caju.transaction.service.domains.responses;

import br.com.challenge.caju.transaction.service.gateways.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TransactionAccountResponse {

    private List<Transaction> transactions;
}
