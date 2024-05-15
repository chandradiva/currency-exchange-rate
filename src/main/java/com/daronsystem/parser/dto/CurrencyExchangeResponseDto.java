package com.daronsystem.parser.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyExchangeResponseDto {

    private String currencyFrom;
    private String amountFrom;
    private String currencyTo;
    private String exchangeRate;
    private String amountTotal;

}
