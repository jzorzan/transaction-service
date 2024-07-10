package br.com.challenge.caju.transaction.service.services.impl;

import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.enums.MCC;
import br.com.challenge.caju.transaction.service.gateways.AccountGateway;
import br.com.challenge.caju.transaction.service.gateways.MerchantGateway;
import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.models.dtos.AccountDTO;
import br.com.challenge.caju.transaction.service.models.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.models.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_BALANCE_NOT_ENOUGH;
import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_SELECTED_BALANCE;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private MerchantGateway merchantGateway;


    @Override
    public TransactionResponse processTransaction(TransactionRequest request) {

        final var account = accountGateway.getAccountById(request.getAccountId());
        if (Objects.isNull(account)) {
            return TransactionResponse.builder()
                    .code("07")
                    .build();
        }

        final BalanceType balanceType = MCC.getBalanceType(request.getMcc());
        log.info(MESSAGE_SELECTED_BALANCE, balanceType);

        final BigDecimal balance = getBalanceByType(account, balanceType);

        if (balance.compareTo(request.getTotalAmount()) >= 0) {

            updateBalance(account, request.getTotalAmount(), balanceType);
            accountGateway.updateAccount(account);

            return TransactionResponse.builder()
                    .code("00")
                    .build();
        }

        if (!BalanceType.CASH.equals(balanceType)
                && account.getCashBalance().compareTo(request.getTotalAmount()) >= 0) {

            log.info(MESSAGE_BALANCE_NOT_ENOUGH);
            updateBalance(account, request.getTotalAmount(), BalanceType.CASH);
            accountGateway.updateAccount(account);
            return TransactionResponse.builder()
                    .code("00")
                    .build();
        }

        return TransactionResponse.builder()
                .code("51")
                .build();
    }

    private BigDecimal getBalanceByType(AccountDTO account, BalanceType balanceType) {
        return switch (balanceType) {
            case FOOD -> account.getFoodBalance();
            case MEAL -> account.getMealBalance();
            case CASH -> account.getCashBalance();
        };
    }

    private void updateBalance(AccountDTO account, BigDecimal amount, BalanceType balanceType) {
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
