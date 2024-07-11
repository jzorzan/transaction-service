package br.com.challenge.caju.transaction.service.services.impl;

import br.com.challenge.caju.transaction.service.domains.dtos.MerchantDTO;
import br.com.challenge.caju.transaction.service.domains.requests.TransactionRequest;
import br.com.challenge.caju.transaction.service.domains.responses.TransactionResponse;
import br.com.challenge.caju.transaction.service.enums.BalanceType;
import br.com.challenge.caju.transaction.service.enums.MCC;
import br.com.challenge.caju.transaction.service.enums.TransactionCode;
import br.com.challenge.caju.transaction.service.gateways.MerchantGateway;
import br.com.challenge.caju.transaction.service.gateways.TransactionGateway;
import br.com.challenge.caju.transaction.service.gateways.entities.Merchant;
import br.com.challenge.caju.transaction.service.services.AccountService;
import br.com.challenge.caju.transaction.service.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

        final String accountId = transactionRequest.getAccountId();
        final BigDecimal totalAmount = transactionRequest.getTotalAmount();
        final String merchantName = transactionRequest.getMerchant();
        String code;

        Optional<Merchant> merchant = merchantGateway.findByMerchantName(merchantName);
        if(merchant.isPresent()){

            String mcc = merchant.get().getMcc();
            final BalanceType balanceType = MCC.getBalanceType(mcc);
            boolean authorized = accountService.authorizeTransaction(accountId,totalAmount, balanceType);

            if (authorized) {
                code = TransactionCode.APPROVED.getCode();
                log.info(MESSAGE_SAVING_TRANSACTION_AUTHORIZED);
                transactionGateway.saveTransaction(transactionRequest);
            } else {
                code = TransactionCode.INSUFFICIENT_FUND.getCode();
            }
        } else {
            code = TransactionCode.NOT_PROCESSED.getCode();
        }

        return TransactionResponse.builder().code(code).build();
    }
}
