package com.daronsystem.parser.controller;

import com.daronsystem.parser.dto.CurrencyExchangeRequestDto;
import com.daronsystem.parser.service.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parser")
public class ParserController {

    @Autowired
    ExchangeCurrencyService exchangeCurrencyService;

    @PostMapping("from-json")
    public ResponseEntity parseJson(@RequestBody List<CurrencyExchangeRequestDto> requests) {
        exchangeCurrencyService.parsingJson(requests);
        return ResponseEntity.ok("Success");
    }

}
