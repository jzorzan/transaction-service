package br.com.challenge.caju.transaction.service.controllers.impl;

import br.com.challenge.caju.transaction.service.controllers.TransactionController;
import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionAccountResponse;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.services.transactions.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction Service")
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/authorize")
    @Override
    public ResponseEntity<TransactionResponse> authorizeTransaction(@RequestBody TransactionRequest request) {
        final var response = transactionService.authorizeTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account/{account_id}")
    @Override
    public ResponseEntity<TransactionAccountResponse> getTransactionByAccount(@PathVariable("account_id") final String accountId) {
        final var response = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(response);
    }
}
