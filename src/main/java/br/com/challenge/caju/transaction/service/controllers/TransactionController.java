package br.com.challenge.caju.transaction.service.controllers;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.services.transactions.TransactionService;
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
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/authorize")
    public ResponseEntity<TransactionResponse> authorizeTransaction(@RequestBody TransactionRequest request) {
        final var response = transactionService.authorizeTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account/{account_id}")
    public ResponseEntity<Object> getTransactionByAccount(@PathVariable("account_id") final String accountId){
        return null;
    }
}
