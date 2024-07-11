package br.com.challenge.caju.transaction.service.controllers;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.enums.MCC;
import br.com.challenge.caju.transaction.service.enums.TransactionCode;
import br.com.challenge.caju.transaction.service.services.transactions.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.challenge.caju.transaction.service.utils.TestConstants.MOCKED_ACCOUNT_ID;
import static br.com.challenge.caju.transaction.service.utils.TestConstants.MOCKED_TOTAL_AMOUNT_100;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private TransactionRequest transactionRequest;
    private TransactionResponse transactionResponse;

    private static final String URL_TRANSACTION_AUTHORIZE = "/transactions/authorize";

    @BeforeEach
    void setUp() {
        transactionRequest = TransactionRequest.builder()
                .accountId(MOCKED_ACCOUNT_ID)
                .mcc(MCC.FOOD_5411.getCode())
                .merchant("Test Merchant")
                .totalAmount(MOCKED_TOTAL_AMOUNT_100)
                .build();

        transactionResponse = TransactionResponse.builder()
                .code(TransactionCode.APPROVED.getCode())
                .build();
    }

    @Test
    void whenAuthorizeTransaction_thenReturnApprovedResponse() throws Exception {

        when(transactionService.authorizeTransaction(any(TransactionRequest.class))).thenReturn(transactionResponse);

        mockMvc.perform(post(URL_TRANSACTION_AUTHORIZE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(transactionResponse)))
                .andExpect(jsonPath("$.code").value(TransactionCode.APPROVED.getCode()));

        Mockito.verify(transactionService, Mockito.times(1)).authorizeTransaction(any(TransactionRequest.class));
    }

    @Test
    void whenAuthorizeTransactionWithInsufficientFunds_thenReturnInsufficientFundsResponse() throws Exception {

        transactionResponse.setCode(TransactionCode.INSUFFICIENT_FUND.getCode());

        when(transactionService.authorizeTransaction(any(TransactionRequest.class))).thenReturn(transactionResponse);

        mockMvc.perform(post(URL_TRANSACTION_AUTHORIZE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(transactionResponse)))
                .andExpect(jsonPath("$.code").value(TransactionCode.INSUFFICIENT_FUND.getCode()));

        Mockito.verify(transactionService, Mockito.times(1)).authorizeTransaction(any(TransactionRequest.class));
    }
}
