package com.daronsystem.parser.service;

import com.daronsystem.parser.dto.CurrencyExchangeDto;
import com.daronsystem.parser.dto.CurrencyExchangeRequestDto;
import com.daronsystem.parser.dto.CurrencyExchangeResponseDto;
import com.daronsystem.parser.dto.CurrencyRateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExchangeCurrencyService {

    @Autowired
    private CurrencyRateService currencyRateService;

    public void parsingJson(List<CurrencyExchangeRequestDto> requests) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, CurrencyRateDto> mapCurrencyRates = currencyRateService.getMapCurrencyRates();
            List<CurrencyExchangeDto> currencyExchanges = requests.stream()
                    .map(request -> this.processExchange(mapCurrencyRates, request))
                    .toList();

            List<CurrencyExchangeResponseDto> responses = currencyExchanges.stream()
                    .map(this::transformResponse)
                    .toList();

            File outputFile = new File("output.json");
            FileWriter fr = null;
            BufferedWriter br = null;

            try {
                fr = new FileWriter(outputFile);
                br = new BufferedWriter(fr);

                br.write(mapper.writeValueAsString(responses));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CurrencyExchangeDto processExchange(Map<String, CurrencyRateDto> mapCurrencyRates, CurrencyExchangeRequestDto request) {
        try {
            String key = request.getCurrencyFrom() + "-" + request.getCurrencyTo();
            CurrencyRateDto currencyRate = mapCurrencyRates.get(key);

            Double exchangeAmount = Double.parseDouble(request.getAmountFrom());
            Double amountTotal = exchangeAmount * currencyRate.getExchangeRate();

            String message = request.getCurrencyFrom()
                    + " to "
                    + request.getCurrencyTo()
                    + ", Rate: "
                    + currencyRate.getExchangeRate()
                    + ", Amount From: "
                    + exchangeAmount
                    + ", Total Amount: "
                    + amountTotal;

            return CurrencyExchangeDto.builder()
                    .currencyFrom(request.getCurrencyFrom())
                    .currencyTo(request.getCurrencyTo())
                    .currencyRate(mapCurrencyRates.get(key))
                    .exchangeAmount(exchangeAmount)
                    .amountTotal(amountTotal)
                    .message(message)
                    .build();
        } catch (Exception e) {
            String message = request.getCurrencyFrom() + " to " + request.getCurrencyTo() + " -> Currency Rate not found";

            return CurrencyExchangeDto.builder()
                    .currencyFrom(request.getCurrencyFrom())
                    .currencyTo(request.getCurrencyTo())
                    .currencyRate(null)
                    .exchangeAmount(Double.parseDouble("0"))
                    .amountTotal(Double.parseDouble("0"))
                    .message(message)
                    .build();
        }
    }

    public CurrencyExchangeResponseDto transformResponse(CurrencyExchangeDto currencyExchange) {
        return CurrencyExchangeResponseDto.builder()
                .currencyFrom(currencyExchange.getCurrencyFrom())
                .amountFrom(String.valueOf(currencyExchange.getExchangeAmount()))
                .currencyTo(currencyExchange.getCurrencyTo())
                .exchangeRate(currencyExchange.getCurrencyRate() != null ? String.valueOf(currencyExchange.getCurrencyRate().getExchangeRate()) : null)
                .amountTotal(String.valueOf(currencyExchange.getAmountTotal()))
                .build();
    }

}
