package br.com.challenge.caju.transaction.service.domains.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String userName;
    private String accountId;
    private BigDecimal foodBalance;
    private BigDecimal mealBalance;
    private BigDecimal cashBalance;
}
