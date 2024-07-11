package br.com.challenge.caju.transaction.service.services.transactions.impl;

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
import br.com.challenge.caju.transaction.service.services.transactions.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_INVALID_FIELD_VALUE;
import static br.com.challenge.caju.transaction.service.utils.constants.Constants.MESSAGE_SAVING_TRANSACTION_AUTHORIZED;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionGateway transactionGateway;

    @Autowired
    private MerchantGateway merchantGateway;


    @Override
    public TransactionResponse authorizeTransaction(TransactionRequest transactionRequest) {

        validateRequest(transactionRequest);

        final String accountId = transactionRequest.getAccountId();
        final BigDecimal totalAmount = transactionRequest.getTotalAmount();
        final String merchantName = transactionRequest.getMerchant();

        Optional<Merchant> merchant = merchantGateway.findByMerchantName(merchantName);

        final String mcc = merchant.isPresent() ? merchant.get().getMcc() : transactionRequest.getMcc();

        final BalanceType balanceType = MCC.getBalanceType(mcc);
        boolean authorized = accountService.authorizeTransaction(accountId, totalAmount, balanceType);

        String code;
        if (authorized) {
            code = TransactionCode.APPROVED.getCode();
            log.info(MESSAGE_SAVING_TRANSACTION_AUTHORIZED);
            transactionGateway.saveTransaction(transactionRequest);
        } else {
            code = TransactionCode.INSUFFICIENT_FUND.getCode();
        }

        return TransactionResponse.builder().code(code).build();
    }

    private void validateRequest(TransactionRequest transactionRequest) {
        Set<String> invalidFields = new HashSet<>();

        if (transactionRequest == null) {
            invalidFields.add("Transaction request body is null");

        } else {
            if (!StringUtils.hasText(transactionRequest.getAccountId())) {
                invalidFields.add("accountId");
            }
            if (transactionRequest.getTotalAmount() == null || transactionRequest.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
                invalidFields.add("totalAmount");
            }
            if (!StringUtils.hasText(transactionRequest.getMerchant())) {
                invalidFields.add("merchant");
            }
        }

        if (!invalidFields.isEmpty()) {
            throw new InvalidFieldException(MESSAGE_INVALID_FIELD_VALUE, invalidFields);
        }
    }

}
