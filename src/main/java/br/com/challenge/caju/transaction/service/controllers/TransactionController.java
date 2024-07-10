package br.com.challenge.caju.transaction.service.controllers;

import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.models.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionResponse> receiveTransaction(@RequestBody TransactionRequest request){

        final var response = transactionService.processTransaction(request);
        return ResponseEntity.ok(response);
    }
}
