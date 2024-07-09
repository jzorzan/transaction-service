package br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.controllers;

import br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.dtos.requests.TransactionRequest;
import br.com.challenge.caju.transaction.authorization.service.transaction.authorization.service.dtos.responses.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping
    public ResponseEntity<TransactionResponse> receiveTransaction(@RequestBody TransactionRequest request){

        return null;

    }
}
