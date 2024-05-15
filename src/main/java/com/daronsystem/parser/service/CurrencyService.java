package com.daronsystem.parser.service;

import com.daronsystem.parser.dto.CurrencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyService {

    public List<CurrencyDto> initCurrency() {
        List<CurrencyDto> currencies = new ArrayList<>();

        currencies.add(CurrencyDto.builder()
                .id(1)
                .currencyCode("USD")
                .currencyName("US Dollar")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(2)
                .currencyCode("EUR")
                .currencyName("Euro")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(3)
                .currencyCode("GBP")
                .currencyName("Pound Sterling")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(4)
                .currencyCode("INR")
                .currencyName("Indian Rupee")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(5)
                .currencyCode("AUD")
                .currencyName("Australian Dollar")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(6)
                .currencyCode("CAD")
                .currencyName("Canadian Dollar")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(7)
                .currencyCode("CHF")
                .currencyName("Swiss Franc")
                .build());

        currencies.add(CurrencyDto.builder()
                .id(8)
                .currencyCode("JPY")
                .currencyName("Japanese Yen")
                .build());

        return currencies;
    }

}
