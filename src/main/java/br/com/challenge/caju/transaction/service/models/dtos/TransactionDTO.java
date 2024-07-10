package br.com.challenge.caju.transaction.service.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private String id;
    private BigDecimal totalAmount;
    private String mcc;
    private String merchant;
}
