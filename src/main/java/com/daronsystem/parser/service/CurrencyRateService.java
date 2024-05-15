package com.daronsystem.parser.service;

import com.daronsystem.parser.dto.CurrencyDto;
import com.daronsystem.parser.dto.CurrencyRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CurrencyRateService {

    @Autowired
    private CurrencyService currencyService;

    public Map<String, CurrencyRateDto> getMapCurrencyRates() {
        return initCurrencyRate().stream()
                .collect(Collectors.toMap(CurrencyRateDto::getKeyRate, Function.identity()));
    }

    public List<CurrencyRateDto> initCurrencyRate() {
        List<CurrencyRateDto> currencyRates = new ArrayList<>();
        List<CurrencyDto> currencies = currencyService.initCurrency();
        Map<String, CurrencyDto> mapCurrencies = currencies.stream()
                .collect(Collectors.toMap(CurrencyDto::getCurrencyCode, Function.identity()));

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("USD"))
                .currencyTo(mapCurrencies.get("EUR"))
                .exchangeRate(0.94)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("EUR"))
                .currencyTo(mapCurrencies.get("GBP"))
                .exchangeRate(0.86)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("GBP"))
                .currencyTo(mapCurrencies.get("INR"))
                .exchangeRate(103.98)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("AUD"))
                .currencyTo(mapCurrencies.get("CAD"))
                .exchangeRate(0.89)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("CAD"))
                .currencyTo(mapCurrencies.get("USD"))
                .exchangeRate(0.73)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("CHF"))
                .currencyTo(mapCurrencies.get("AUD"))
                .exchangeRate(1.69)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("USD"))
                .currencyTo(mapCurrencies.get("CHF"))
                .exchangeRate(0.91)
                .build());

        currencyRates.add(CurrencyRateDto.builder()
                .currencyFrom(mapCurrencies.get("JPY"))
                .currencyTo(mapCurrencies.get("USD"))
                .exchangeRate(0.0065)
                .build());

        return currencyRates;
    }

}
