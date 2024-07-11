package br.com.challenge.caju.transaction.service.controllers;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionAccountResponse;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionController {


    @Operation(summary = "Authorize a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction authorized successfully with code 00(PROCESSED)",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "200", description = "Invalid transaction request with code 07(NOT PROCESSED) or 51(INSUFFICIENT FUND)",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class)))
    })
    ResponseEntity<TransactionResponse> authorizeTransaction(@RequestBody TransactionRequest request);

    @Operation(summary = "Get transactions by account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved by account id",
                    content = @Content(schema = @Schema(implementation = TransactionAccountResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account ID")
    })
    ResponseEntity<TransactionAccountResponse> getTransactionByAccount(@Parameter(description = "Account ID", required = true)
                                                                       @PathVariable("account_id") final String accountId);
}
