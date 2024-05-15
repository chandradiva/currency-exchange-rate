package com.daronsystem.parser.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyDto {

    private int id;
    private String currencyCode;
    private String currencyName;

}
