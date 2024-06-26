package com.daronsystem.parser.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyExchangeDto {

    private String currencyFrom;
    private String currencyTo;
    private CurrencyRateDto currencyRate;
    private Double exchangeAmount;
    private Double amountTotal;
    private String message;

}
