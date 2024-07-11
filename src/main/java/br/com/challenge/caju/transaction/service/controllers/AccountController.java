package br.com.challenge.caju.transaction.service.controllers;


import br.com.challenge.caju.transaction.service.domains.responses.AccountResponse;
import br.com.challenge.caju.transaction.service.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getTransactionsByAccount(@PathVariable final String accountId){
        final var response = accountService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(response);
    }
}
