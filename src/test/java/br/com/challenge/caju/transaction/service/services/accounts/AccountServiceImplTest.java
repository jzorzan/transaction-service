package br.com.challenge.caju.transaction.service.services.accounts;

import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Account;
import br.com.challenge.caju.transaction.service.mappers.AccountMapper;
import br.com.challenge.caju.transaction.service.services.accounts.impl.AccountServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountGateway accountGateway;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder()
                .accountId(MOCKED_ACCOUNT_ID)
                .cashBalance(MOCKED_TOTAL_AMOUNT_100)
                .foodBalance(MOCKED_TOTAL_AMOUNT_100)
                .mealBalance(MOCKED_TOTAL_AMOUNT_100)
                .build();
    }

    @Test
    void whenSufficientBalance_thenAuthorizeTransaction() {
        when(accountGateway.findById(anyString())).thenReturn(Optional.of(account));

        boolean result = accountService.authorizeTransaction("12345", new BigDecimal("50.00"), BalanceType.FOOD);

        assertTrue(result);
        assertEquals(new BigDecimal("50.00"), account.getFoodBalance());
        verify(accountGateway, times(1)).updateAccount(account);
    }

    @Test
    void whenInsufficientBalance_thenAuthorizeTransactionWithCash() {
        account.setFoodBalance(new BigDecimal("30.00"));
        when(accountGateway.findById(anyString())).thenReturn(Optional.of(account));

        boolean result = accountService.authorizeTransaction("12345", new BigDecimal("50.00"), BalanceType.FOOD);

        assertTrue(result);
        assertEquals(new BigDecimal("30.00"), account.getFoodBalance());
        assertEquals(new BigDecimal("50.00"), account.getCashBalance());
        verify(accountGateway, times(1)).updateAccount(account);
    }

    @Test
    void whenInsufficientBalanceAndCash_thenDoNotAuthorizeTransaction() {
        account.setFoodBalance(new BigDecimal("30.00"));
        account.setCashBalance(new BigDecimal("30.00"));
        when(accountGateway.findById(anyString())).thenReturn(Optional.of(account));

        boolean result = accountService.authorizeTransaction("12345", new BigDecimal("50.00"), BalanceType.FOOD);

        assertFalse(result);
        assertEquals(new BigDecimal("30.00"), account.getFoodBalance());
        assertEquals(new BigDecimal("30.00"), account.getCashBalance());
        verify(accountGateway, times(0)).updateAccount(account);
    }
}