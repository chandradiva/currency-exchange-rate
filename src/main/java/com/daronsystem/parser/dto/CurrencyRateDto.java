package com.daronsystem.parser.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyRateDto {

    private CurrencyDto currencyFrom;
    private CurrencyDto currencyTo;
    private Double exchangeRate;
    private String keyRate;

    public String getKeyRate() {
        return currencyFrom.getCurrencyCode() + "-" + currencyTo.getCurrencyCode();
    }

}
