package br.com.challenge.caju.transaction.service.services.accounts.impl;

import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Account;
import br.com.challenge.caju.transaction.service.mappers.AccountMapper;
import br.com.challenge.caju.transaction.service.services.accounts.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_BALANCE_NOT_ENOUGH;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private AccountMapper accountMapper;

    public boolean authorizeTransaction(final String accountId, final BigDecimal amount, final BalanceType balanceType) {

        Optional<Account> optionalAccount = accountGateway.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal balance = getBalanceByType(account, balanceType);

            if (balance.compareTo(amount) >= 0) {
                debitBalance(account, balanceType, amount);
                accountGateway.updateAccount(account);
                return true;
            } else if (balanceType != BalanceType.CASH && account.getCashBalance().compareTo(amount) >= 0) {

                log.info(MESSAGE_BALANCE_NOT_ENOUGH);
                debitBalance(account, BalanceType.CASH, amount);
                accountGateway.updateAccount(account);
                return true;
            }
        }
        return false;
    }

    private BigDecimal getBalanceByType(Account account, BalanceType balanceType) {
        return switch (balanceType) {
            case FOOD -> account.getFoodBalance();
            case MEAL -> account.getMealBalance();
            case CASH -> account.getCashBalance();
        };
    }

    private void debitBalance(Account account, BalanceType balanceType, BigDecimal amount) {
        switch (balanceType) {
            case FOOD:
                account.setFoodBalance(account.getFoodBalance().subtract(amount));
                break;
            case MEAL:
                account.setMealBalance(account.getMealBalance().subtract(amount));
                break;
            case CASH:
                account.setCashBalance(account.getCashBalance().subtract(amount));
                break;
        }
    }
}
