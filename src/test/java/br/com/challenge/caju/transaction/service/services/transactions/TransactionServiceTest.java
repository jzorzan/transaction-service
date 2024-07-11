package br.com.challenge.caju.transaction.service.services.transactions;

import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.enums.MCC;
import br.com.challenge.caju.transaction.service.enums.TransactionCode;
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

import static br.com.challenge.caju.transaction.service.utils.TestConstants.MOCKED_ACCOUNT_ID;
import static br.com.challenge.caju.transaction.service.utils.TestConstants.MOCKED_TOTAL_AMOUNT_100;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

    private TransactionRequest transactionRequest;

    @BeforeEach
    void setUp() {
        transactionRequest = TransactionRequest.builder()
                .accountId(MOCKED_ACCOUNT_ID)
                .mcc(MCC.FOOD_5411.getCode())
                .merchant("Test Merchant")
                .totalAmount(MOCKED_TOTAL_AMOUNT_100)
                .build();
    }

    @Test
    void whenMerchantIsKnown_thenTransactionIsApproved() {
        Merchant merchant = new Merchant();
        merchant.setMcc(MCC.FOOD_5411.getCode());

        when(merchantGateway.findByMerchantName(anyString())).thenReturn(Optional.of(merchant));
        when(accountService.authorizeTransaction(anyString(), any(BigDecimal.class), any(BalanceType.class))).thenReturn(true);

        TransactionResponse response = transactionService.authorizeTransaction(transactionRequest);

        assertEquals(TransactionCode.APPROVED.getCode(), response.getCode());
        verify(transactionGateway, times(1)).saveTransaction(any(TransactionRequest.class));
        verify(accountService, times(1)).authorizeTransaction("12345",
                new BigDecimal("100.00"), BalanceType.FOOD);
    }


    @Test
    void whenMerchantIsKnown_thenTransactionIsNotApprovedDueToInsufficientFunds() {
        Merchant merchant = new Merchant();
        merchant.setMcc(MCC.FOOD_5411.getCode());

        when(merchantGateway.findByMerchantName(anyString())).thenReturn(Optional.of(merchant));
        when(accountService.authorizeTransaction(anyString(), any(BigDecimal.class), any(BalanceType.class))).thenReturn(false);

        TransactionResponse response = transactionService.authorizeTransaction(transactionRequest);

        assertEquals(TransactionCode.INSUFFICIENT_FUND.getCode(), response.getCode());
        verify(transactionGateway, times(0)).saveTransaction(any(TransactionRequest.class));
        verify(accountService, times(1)).authorizeTransaction("12345", new BigDecimal("100.00"), BalanceType.FOOD);
    }

    @Test
    void whenMerchantIsUnknown_thenTransactionIsApprovedWithDefaultBalanceType() {
        when(merchantGateway.findByMerchantName(anyString())).thenReturn(Optional.empty());
        when(accountService.authorizeTransaction(anyString(), any(BigDecimal.class), any(BalanceType.class))).thenReturn(true);

        TransactionResponse response = transactionService.authorizeTransaction(transactionRequest);

        assertEquals(TransactionCode.APPROVED.getCode(), response.getCode());
        verify(transactionGateway, times(1)).saveTransaction(any(TransactionRequest.class));
        verify(accountService, times(1)).authorizeTransaction("12345", new BigDecimal("100.00"), BalanceType.CASH);
    }

    @Test
    void whenMerchantIsUnknown_thenTransactionIsNotApprovedWithDefaultBalanceType() {
        transactionRequest.setMerchant("unknown");

        when(merchantGateway.findByMerchantName(anyString())).thenReturn(Optional.empty());
        when(accountService.authorizeTransaction(anyString(), any(BigDecimal.class), any(BalanceType.class))).thenReturn(false);

        TransactionResponse response = transactionService.authorizeTransaction(transactionRequest);

        assertEquals(TransactionCode.INSUFFICIENT_FUND.getCode(), response.getCode());
        verify(transactionGateway, times(0)).saveTransaction(any(TransactionRequest.class));
        verify(accountService, times(1)).authorizeTransaction("12345", new BigDecimal("100.00"), BalanceType.CASH);
    }


}
