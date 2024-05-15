package com.daronsystem.parser.service;

import com.daronsystem.parser.dto.CurrencyExchangeDto;
import com.daronsystem.parser.dto.CurrencyExchangeRequestDto;
import com.daronsystem.parser.dto.CurrencyRateDto;
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
            Map<String, CurrencyRateDto> mapCurrencyRates = currencyRateService.getMapCurrencyRates();
            List<CurrencyExchangeDto> currencyExchanges = requests.stream()
                    .map(request -> this.transformObject(mapCurrencyRates, request))
                    .toList();

            File outputFile = new File("output.txt");
            FileWriter fr = null;
            BufferedWriter br = null;

            try {
                fr = new FileWriter(outputFile);
                br = new BufferedWriter(fr);

                for (CurrencyExchangeDto currencyExchange : currencyExchanges) {
                    System.out.println(currencyExchange.getMessage());
                    br.write(currencyExchange.getMessage() + System.lineSeparator());
                }
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

    public CurrencyExchangeDto transformObject(Map<String, CurrencyRateDto> mapCurrencyRates, CurrencyExchangeRequestDto request) {
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
                    .currencyRate(mapCurrencyRates.get(key))
                    .exchangeAmount(exchangeAmount)
                    .amountTotal(amountTotal)
                    .message(message)
                    .build();
        } catch (Exception e) {
            String message = request.getCurrencyFrom() + " to " + request.getCurrencyTo() + " -> Currency Rate not found";

            return CurrencyExchangeDto.builder()
                    .currencyRate(null)
                    .exchangeAmount(Double.parseDouble("0"))
                    .amountTotal(Double.parseDouble("0"))
                    .message(message)
                    .build();
        }
    }

}
