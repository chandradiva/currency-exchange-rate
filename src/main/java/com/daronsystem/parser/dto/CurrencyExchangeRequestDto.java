package com.daronsystem.parser.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyExchangeRequestDto {

    private String currencyFrom;
    private String amountFrom;
    private String currencyTo;

}
