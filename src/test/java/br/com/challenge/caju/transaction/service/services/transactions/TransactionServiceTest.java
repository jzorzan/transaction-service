package br.com.challenge.caju.transaction.service.services.transactions;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.enums.MCC;
import br.com.challenge.caju.transaction.service.enums.TransactionCode;
import br.com.challenge.caju.transaction.service.exceptions.InvalidFieldException;
import br.com.challenge.caju.transaction.service.gateways.MerchantGateway;
import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Merchant;
import br.com.challenge.caju.transaction.service.services.accounts.AccountService;
import br.com.challenge.caju.transaction.service.services.transactions.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_INVALID_FIELD_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionGateway transactionGateway;

    @Mock
    private MerchantGateway merchantGateway;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionRequest validTransactionRequest;
    private Merchant validMerchant;

    @BeforeEach
    void setUp() {
        validTransactionRequest = TransactionRequest.builder()
                .accountId("validAccountId")
                .totalAmount(BigDecimal.TEN)
                .merchant("validMerchant")
                .build();

        validMerchant = Merchant.builder()
                .merchantName("validMerchant")
                .mcc(MCC.FOOD_5411.getCode())
                .build();
    }

    @Test
    void authorizeTransaction_SuccessfulAuthorization_ReturnsApproved() {


        when(merchantGateway.findByMerchantName("validMerchant")).thenReturn(Optional.of(validMerchant));
        when(accountService.authorizeTransaction("validAccountId", BigDecimal.TEN, BalanceType.FOOD))
                .thenReturn(true);


        TransactionResponse response = transactionService.authorizeTransaction(validTransactionRequest);


        assertEquals(TransactionCode.APPROVED.getCode(), response.getCode());
        verify(transactionGateway, times(1)).saveTransaction(validTransactionRequest);
    }


    @Test
    void authorizeTransaction_InsufficientFunds_ReturnsInsufficientFund() {

        when(merchantGateway.findByMerchantName("validMerchant")).thenReturn(Optional.of(validMerchant));
        when(accountService.authorizeTransaction("validAccountId", BigDecimal.TEN, BalanceType.FOOD))
                .thenReturn(false);


        TransactionResponse response = transactionService.authorizeTransaction(validTransactionRequest);


        assertEquals(TransactionCode.INSUFFICIENT_FUND.getCode(), response.getCode());
        verify(transactionGateway, times(0)).saveTransaction(any(TransactionRequest.class));
    }

    @Test
    void authorizeTransaction_UnknownMerchant_ApprovesWithDefaultBalanceType() {

        validTransactionRequest.setMerchant("unknown");


        when(merchantGateway.findByMerchantName("unknown")).thenReturn(Optional.empty());
        when(accountService.authorizeTransaction("validAccountId", BigDecimal.TEN, BalanceType.CASH))
                .thenReturn(true);


        TransactionResponse response = transactionService.authorizeTransaction(validTransactionRequest);


        assertEquals(TransactionCode.APPROVED.getCode(), response.getCode());
        verify(transactionGateway, times(1)).saveTransaction(validTransactionRequest);
    }

    @Test
    void authorizeTransaction_UnknownMerchant_NotApprovedWithDefaultBalanceType() {

        validTransactionRequest.setMerchant("unknown");

        when(merchantGateway.findByMerchantName("unknown")).thenReturn(Optional.empty());
        when(accountService.authorizeTransaction("validAccountId", BigDecimal.TEN, BalanceType.CASH))
                .thenReturn(false);


        TransactionResponse response = transactionService.authorizeTransaction(validTransactionRequest);

        assertEquals(TransactionCode.INSUFFICIENT_FUND.getCode(), response.getCode());
        verify(transactionGateway, times(0)).saveTransaction(any(TransactionRequest.class));
    }

    @Test
    void validateRequest_InvalidRequestBody_ThrowsInvalidFieldException() {

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> transactionService.authorizeTransaction(null));

        assertEquals(MESSAGE_INVALID_FIELD_VALUE, exception.getMessage());
        assertTrue(exception.getFields().contains("Transaction request body is null"));
    }

    @Test
    void validateRequest_EmptyAccountId_ThrowsInvalidFieldException() {

        final var request = TransactionRequest.builder()
                .accountId(null)
                .totalAmount(BigDecimal.TEN)
                .merchant("validMerchant")
                .build();


        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> transactionService.authorizeTransaction(request));

        assertEquals(MESSAGE_INVALID_FIELD_VALUE, exception.getMessage());
        assertTrue(exception.getFields().contains("accountId"));
    }

    @Test
    void validateRequest_NullTotalAmount_ThrowsInvalidFieldException() {

        final var request = TransactionRequest.builder()
                .accountId("validAccountId")
                .totalAmount(null)
                .merchant("validMerchant")
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> transactionService.authorizeTransaction(request));

        assertEquals(MESSAGE_INVALID_FIELD_VALUE, exception.getMessage());
        assertTrue(exception.getFields().contains("totalAmount"));
    }

    @Test
    void validateRequest_ZeroTotalAmount_ThrowsInvalidFieldException() {

        final var request = TransactionRequest.builder()
                .accountId("validAccountId")
                .totalAmount(BigDecimal.ZERO)
                .merchant("validMerchant")
                .build();

        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> transactionService.authorizeTransaction(request));

        assertEquals(MESSAGE_INVALID_FIELD_VALUE, exception.getMessage());
        assertTrue(exception.getFields().contains("totalAmount"));
    }

    @Test
    void validateRequest_EmptyMerchant_ThrowsInvalidFieldException() {

        final var request = TransactionRequest.builder()
                .accountId("validAccountId")
                .totalAmount(BigDecimal.TEN)
                .merchant("")
                .build();

        // Test & Verify
        InvalidFieldException exception = assertThrows(InvalidFieldException.class,
                () -> transactionService.authorizeTransaction(request));

        assertEquals("Invalid transaction request. Required fields are missing or invalid.", exception.getMessage());
        assertTrue(exception.getFields().contains("merchant"));
    }
}
