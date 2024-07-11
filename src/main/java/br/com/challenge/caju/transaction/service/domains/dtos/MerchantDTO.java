package br.com.challenge.caju.transaction.service.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDTO {

    private String id;
    private String merchantName;
    private String mcc;
}
