package com.daronsystem.parser.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyExchangeDto {

    private CurrencyRateDto currencyRate;
    private Double exchangeAmount;
    private Double amountTotal;
    private String message;

}
