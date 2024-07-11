package br.com.challenge.caju.transaction.service.domains.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;

    @JsonProperty("mcc")
    private String mcc;

    @JsonProperty("merchant")
    private String merchant;
}
